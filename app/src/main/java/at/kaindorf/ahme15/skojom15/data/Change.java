package at.kaindorf.ahme15.skojom15.data;

/** Datenhaltungsklasse zur Aufnahme einer Wechselmöglichkeit */
public class Change
{
  private final int count;
  private final int value;

  /** Konstruktor für Wechselmöglichkeit
   * @param count Anzahl der Münzen
   * @param value Münzwert */
  public Change(int count, int value)
  {
    this.count = count;
    this.value = value;
  }

  public int getCount()
  {
    return count;
  }

  public int getValue()
  {
    return value;
  }
  
  @Override
  public String toString()
  {
    return String.format("(%2dx%2d)", count, value);
  }
}
