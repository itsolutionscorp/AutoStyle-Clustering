import java.util.Comparator;

public class TrayComparator implements Comparator<Tray>
{
    @Override
    public int compare(Tray t1, Tray t2)
    {
        // Assume neither tray is null. 
        // If distance is shorter, the priority should be larger.
    	if (t1 == null) throw new IllegalStateException();
    	if (t2 == null) throw new IllegalStateException();
        
    	return (int) (t1.distance() - t2.distance());
    }
}