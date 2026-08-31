import java.util.LinkedList;
import java.util.ListIterator;

public class Business
{
    /**
      * Removes every nth element from the linked list
      *
      * @param employeeNames 
      * @param n                 
     */
    public static void downsize(LinkedList<String> employeeNames, int n)
    {
        ListIterator<String> iterator = employeeNames.listIterator();
        int x = 0;
        while (iterator.hasNext())
        {
            iterator.next();
            x++;
            if (x % n == 0)
            {
                iterator.remove();
            }
        }
    }
} 
