package at.kaindorf.ahme15.skojom15.gui;

import at.kaindorf.ahme15.skojom15.*;
import at.kaindorf.ahme15.skojom15.data.Change;
import at.kaindorf.ahme15.skojom15.data.Changes;

import java.util.*;


/** Rechnerklasse zur Berechnung aller Wechselmöglichkeiten */
public class ChangeCalculator
{
  private final int amount;
  private final List<Integer> coins;
  private final List<Changes> changes = new ArrayList<>();

  public ChangeCalculator(final int amount, final List<Integer> coins)
    throws Exception
  {
    this.amount = amount;
    this.coins = coins;
    if (amount<0)
      throw new Exception("amount must not be negative!");
    if (coins.isEmpty())
      throw new Exception("list of coins must not be empty!");
    calculate();
  }

  private int getMax(final int index)
  {
    return amount / coins.get(index);
  }

  private boolean inc(final int[] ind, final int[] max)
  {
    final int size = ind.length;
    int pos = 0;
    while (true)
    {
      if (++ind[pos]<max[pos])
        return false;
      if (++pos == size)
        return true;
      ind[pos-1]=0;
    }
  }

  private void calculate()
  {
    final int size = coins.size();
    final int[]
      ind = new int[size],
      val = new int[size],
      max = new int[size];
    for (int i=0;i<size;i++)
    {
      val[i] = coins.get(i);
      max[i] = amount / val[i];
    }
    do
    {
      int summe = 0;
      for (int i=0;i<size;i++)
      {
        summe += ind[i] * val[i];
        if (summe>amount)
          break;
      }
      if (summe == amount)
      {
        final List<Change> changes = new ArrayList<>();
        for (int i=0;i<size;i++)
          if (ind[i] != 0)
            changes.add(new Change(ind[i], val[i]));
        this.changes.add(new Changes(changes));
      }
    }
    while (!inc(ind, max));
  }

  public List<Changes> getChanges()
  {
    return changes;
  }
  
  public static void main(String[] args)
  {
    try
    {
      final int amount = 40;
      final List<Integer> coins = Arrays.asList(2, 5, 10);
      final ChangeCalculator cc = new ChangeCalculator(amount, coins);
      final List<Changes> changess = cc.getChanges();
      for (Changes changes : changess)
        System.out.println(changes);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }
}
