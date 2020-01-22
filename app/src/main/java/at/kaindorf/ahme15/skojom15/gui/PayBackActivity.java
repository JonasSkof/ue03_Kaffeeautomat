package at.kaindorf.ahme15.skojom15.gui;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import at.kaindorf.ahme15.skojom15.R;
import at.kaindorf.ahme15.skojom15.data.Changes;
import at.kaindorf.ahme15.skojom15.data.Coffee;

public class PayBackActivity extends AppCompatActivity {

 private RecyclerView recyclerView = null;

 public static class MyAdapter extends RecyclerView.Adapter <MyAdapter.MyViewHolder> {

  private final List<TextAndImage> textAndImages = new ArrayList<>();


  public void add(CharSequence text, Drawable image) {
   textAndImages.add(new TextAndImage(text, image));
  }

  public void add(CharSequence text) {
   textAndImages.add(new TextAndImage(text, null));
  }

  static class TextAndImage {
   private final CharSequence text;
   private final Drawable image;

   public TextAndImage(CharSequence text) {
    this(text, null);

   }

   TextAndImage(CharSequence text, Drawable image) {
    this.text = text;
    this.image = image;
   }

   public CharSequence getText() {
    return text;
   }

   public Drawable getImage() {
    return image;
   }

  }

  @NonNull
  @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
   final CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
   return new MyViewHolder(cardView);
  }

  @Override
  public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
   final TextAndImage textAndImage = textAndImages.get(position);

   holder.bindText(textAndImage.getText());
   final Drawable drawable = textAndImage.getImage();
   holder.bindImage(drawable);
   holder.getCardView().setCardBackgroundColor( 0x1000000);
  }

  @Override
  public int getItemCount() {
   return textAndImages.size();
  }

  public static class MyViewHolder extends RecyclerView.ViewHolder {
   private final CardView cardView;
   private final TextView textView;
   private final ImageView imageView;


   public MyViewHolder(@NonNull CardView itemView) {
    super(itemView);
    cardView = itemView;
    textView = cardView.findViewById(R.id.cardText);
    imageView = cardView.findViewById(R.id.cardImage);
   }

   public void bindText(CharSequence text) {
    textView.setText(text);
   }

   public void bindImage(Drawable image) {
    imageView.setImageDrawable(image);
   }

   public CardView getCardView() {
    return cardView;
   }
  }
 }

 @Override
 protected void onCreate(@Nullable Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);

  recyclerView = new RecyclerView(this);

  final RecyclerView.LayoutManager layoutManager = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE
          ? new GridLayoutManager(this, 2)
          : new LinearLayoutManager(this);

  setContentView(recyclerView);
  recyclerView.setLayoutManager(layoutManager);

  final MyAdapter adapter;
  recyclerView.setAdapter(adapter = new MyAdapter());


  try{
       Bundle bundle = getIntent().getExtras();
       int amount=0;
       // NumberFormat n = NumberFormat.getInstance();
       // n.setMaximumFractionDigits(2);
      if(bundle != null){
          amount = bundle.getInt("amount");
      }

       Coffee[] coffees = Coffee.values();

   final ChangeCalculator cc = new ChangeCalculator(amount, Arrays.asList(5,10,20,50,100,200));
   final List<Changes> changess = cc.getChanges();
   for (Changes changes : changess) {
    System.out.println("**" + changes);
    adapter.add((CharSequence) changes);
   }

/*
       for (int ma1 = 0; ma1 <= amount/coffees[0].getPrice(); ma1++) {
        for (int ma2 = 0; ma2 <= amount/coffees[1].getPrice(); ma2++) {
         for(int ma3 = 0; ma3 <= amount/coffees[2].getPrice(); ma3++) {
          for(int ma4 = 0; ma4 <= amount/coffees[3].getPrice(); ma4++) {ß
           for(int ma5 = 0; ma5 <= amount/coffees[4].getPrice(); ma5++) {
            for(int ma6 = 0; ma6 <= amount/coffees[5].getPrice(); ma6++) {
             if(ma1*coffees[0].getPrice()+ma2*coffees[1].getPrice()+ma3*coffees[2].getPrice()+ma4*coffees[3].getPrice()+ma5*coffees[4].getPrice()+ma6*coffees[5].getPrice() == amount)
             {
              if(ma1 > 0)
              adapter.add(String.format(Locale.GERMANY, "%d", ma1) + "x", getDrawable(R.drawable.euro_2));
              if(ma2 > 0)
              adapter.add(String.format(Locale.GERMANY, "%d", ma2) + "x", getDrawable(R.drawable.euro_1));
              if(ma3 > 0)
              adapter.add(String.format(Locale.GERMANY, "%d", ma3) + "x", getDrawable(R.drawable.cent_50));
              if(ma4 > 0)
              adapter.add(String.format(Locale.GERMANY, "%d", ma4) + "x", getDrawable(R.drawable.cent_20));
              if(ma5 > 0)
              adapter.add(String.format(Locale.GERMANY, "%d", ma5) + "x", getDrawable(R.drawable.cent_10));
              if(ma6 > 0)
              adapter.add(String.format(Locale.GERMANY, "%d", ma6) + "x", getDrawable(R.drawable.cent_5));

              adapter.add("",null);
             }
            }
           }
          }
         }
        }
       }
       */

      setContentView(recyclerView);


  }catch (Exception ex)
  {
    ex.printStackTrace();
  }

  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
 }

 @Override
 public boolean onSupportNavigateUp() {
  onBackPressed();
  return true;
 }


}
