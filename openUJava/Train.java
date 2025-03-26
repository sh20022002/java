import java.util.Scanner;

public class Train{
    public static void main (String [] args){
        // כאן אפשר להכניס תיעוד המסביר על התכנית 
        // The program takes the speed and Time of travel of two Trains
        // and prints the distance after the two Trains stoped
        /* 
        Scanner takes 4 intrgers as input:
            int: v1 - The Speed(kph) of Train 1
            int: t1 - The Travel Time(Minutes) of Train 1
            int: v2 - The Speed(kph) of Train 2
            int: t2 - The Travel Time(Minutes) of Train 2
        print: The distance between the Trains is double(tdb) km.
        **/
                
      
        Scanner scan = new Scanner (System.in);
        System.out.println ("Please enter 4 integers ");
        System.out.println ("Please enter the speed of train 1:");
        int v1 = scan.nextInt();
        System.out.println ("Please enter the time of train 1:");
        int t1 = scan.nextInt();
        System.out.println ("Please enter the speed of train 2:");
        int v2 = scan.nextInt();
        System.out.println ("Please enter the time of train 2:");
        int t2 = scan.nextInt();
        
        //calculate the ditance traveled 
        // speed(per hour)*(time after conversion from minute to hour without casting) 

        double d1 = v1*(t1/60.0);
        double d2 = v2*(t2/60.0);

        // absolute value of the distance
        double tdb = Math.abs(d2 - d1);
        // print the distance and close the Scanner
        System.out.printf("The distance between the Trains is %.1f km.%n", tdb);
        scan.close();

 } // end of method main
} //end of class Train
    

