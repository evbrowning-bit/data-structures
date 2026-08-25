import java.util.LinkedList;
import java.util.ListIterator;

/**
 * This program demonstrates the LinkedList class
 * and ListIterator class.
*/
public class ListDemo
{
    public static void main(String[] args)
    {
        LinkedList<String> staff = new LinkedList<>();
        staff.addLast("Tony");
        staff.addLast("Steve");
        staff.addLast("Wanda");
        staff.addLast("Dr. Strange");
        
        System.out.println(staff);

        //The list is currently t,s,w,d
        /*
        the list iterator method creates a new list iterator at the begining of the list
        the | is used to represent the iterator position
         */
        ListIterator <String> iterator = staff.listIterator();
        //  |t,s,w,d
        // the next method advances iterator over the next element in list
        iterator.next(); //T|SWD
        //next method returns elemnt the iterator passes over
        String avenger = iterator.next();
        System.out.println(avenger); //Prints steve

        iterator.add("Natasha");
        iterator.add("Bruce");

        System.out.println(staff);
        //remove method removes the element returned by last call to next or previous
        //remove method can ONLY be called after calling next or previous
        //remove method CAN NOT be called after calling add
        iterator.next();
        iterator.remove();

        System.out.println();

        //the set method updates the element returned by the last call to next or previous

        iterator.previous();
        iterator.set("T'Challa"); //bruce is replaced

        //has next method is used to determine if there is a next node after the iterator
        iterator = staff.listIterator();
        while (iterator.hasNext());
        {
            String n = iterator.next();
            if(n.equals("Natasha"))
            {
                iterator.remove();
            }
        }
        System.out.println(staff);

        //Enhanced for loops work with linked lists
        //The enhanced for loop automatically creates iterator
        for(String n: staff)
        {
            System.out.print(n+" ");
        }


        
        iterator= staff.listIterator();
        while(iterator.hasNext())
        //Concurrent modification exeption-- CAN NOT modify a linked list while using an iterator 
        {
            String n = iterator.next();
            if(n.equals("Tony"))
            {
                //staff.remove("Tony");//ERROR!!!!!
            }
        }
        for(String n: staff)
        {
            if(n.equals("Tony"));
            {
                //staff.add("Peter");//ERROR!!!!!
            }

        }






        
    
    }
}
