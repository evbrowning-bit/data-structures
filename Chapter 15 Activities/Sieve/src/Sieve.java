import java.util.Scanner;


/**
 * A program that implements the sieve of Eratosthenes.
*/
public class Sieve
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Compute primes up to which integer?");
        int n = in.nextInt();

        // Your work goes here
        boolean[] prime = new boolean[n+1];
        for(int i=0;i<n;i++)
        {
            for(int k=1; k<i;k++)
            {
                if(i%k != 0)
                {
                    prime[i]=false;
                }
                else
                {
                    prime[i]=true;
                }
            }
        }

        for(int i=0;i<n;i++)
        {
            if(prime[i]==true)
            {
                System.out.println(i);
            }
        }







    }
}
