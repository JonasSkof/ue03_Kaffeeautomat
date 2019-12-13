package at.kaindorf.ahme15.skojom15.gui;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import at.kaindorf.ahme15.skojom15.R;

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
        NumberFormat n = NumberFormat.getInstance();
        n.setMaximumFractionDigits(2);

      if(bundle != null) {
       double amount = bundle.getDouble("amount");

       if(amount != 0.00) {

        amount += 0.0000001;

        int a2 = (int) (amount / 2.00);
        amount -= a2 * 2;

        int a1 = (int) (amount / 1.00);
        amount -= a1;

        int a05 = (int) (amount / 0.5);
        amount -= a05 * 0.5;

        int a02 = (int) (amount / 0.2);
        amount -= a02 * 0.2;

        int a01 = (int) (amount / 0.1);
        amount -= a01 * 0.1;

        int a005 = (int) (amount / 0.05);


        if (a2 > 0)
         adapter.add(String.format(Locale.GERMANY, "%d", a2) + "x", getDrawable(R.drawable.euro_2));

        if (a1 > 0)
         adapter.add(String.format(Locale.GERMANY, "%d", a1) + "x", getDrawable(R.drawable.euro_1));

        if (a05 > 0)
         adapter.add(String.format(Locale.GERMANY, "%d", a05) + "x", getDrawable(R.drawable.cent_50));

        if (a02 > 0)
         adapter.add(String.format(Locale.GERMANY, "%d", a02) + "x", getDrawable(R.drawable.cent_20));

        if (a01 > 0)
         adapter.add(String.format(Locale.GERMANY, "%d", a01) + "x", getDrawable(R.drawable.cent_10));

        if (a005 > 0)
         adapter.add(String.format(Locale.GERMANY, "%d", a005) + "x", getDrawable(R.drawable.cent_5));
        }
      }
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
