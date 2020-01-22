package at.kaindorf.ahme15.skojom15.data;

import java.util.List;

public class Changes
{
  private final List<Change> changes;

  public Changes(List<Change> changes)
  {
    this.changes = changes;
  }

  public List<Change> getChanges()
  {
    return changes;
  }

  public boolean add(Change change)
  {
    return changes.add(change);
  }
  
  @Override
  public String toString()
  {
    final StringBuilder sb = new StringBuilder();
    for (Change change : changes)
      sb.append(change).append(' ');
    return sb.toString();
  }
}
