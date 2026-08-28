import java.util.LinkedList;

/**
 * Business utility methods.
*/
public class Business
{
    /**
      * Removes every nth element from the linked list
      *
      * @param employeeNames the linked list to remove from
      * @param n                 the parameter to determine "nth"
     */
    public static void downsize(LinkedList<String> employeeNames, int n)
    {
        
        for(int i=0;i<employeeNames.size();i++)
        {
            
            if(i==n)
            {
                employeeNames.remove(i);
                
                n+=n;
            }
            
        }
    }

}
