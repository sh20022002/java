import java.util.Scanner;

public class Trapezoid{
    public static void main (String [] args){
        // כאן אפשר להכניס תיעוד המסביר על התכנית 
        /*
         calculate Trapezoid area and perimeter
          
         int: x1 ,y1 and x2 ,y2 x and y of one of the left points
         int: length1 ,length2 the length of the bases

         output: the value of area and perimeter
         **/
        Scanner scan = new Scanner (System.in);

        // get user input
        System.out.println("Please enter the left point coordinates of the base followed by its length:");
        int x1 = scan.nextInt();
        int y1 = scan.nextInt();
        int length1 = scan.nextInt();

        System.out.println ("Please enter the left point coordinates of the other base followed by its length:");
        int x2 = scan.nextInt();
        int y2 = scan.nextInt();
        int length2 = scan.nextInt();

        // the bases are parlel to x
        int x3 = length1 + x1;
        int y3 = y1;
        
        int x4 = length2 + x2;
        int y4 = y2;

        // bases * the absolute hight devided by 2
        double area = ((length1 + length2) * Math.abs(y1 - y2)) / 2;

        // distance of Trapezoid legs
        // x1 ,y1 and x2 ,y2 the left leg
        // x3 ,y3 and x4 ,y4 the right leg
        double length3= Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2 ));
        double length4= Math.sqrt(Math.pow(x3 - x4, 2) + Math.pow(y3 - y4, 2));
        
        double circumference = length1 + length2 + length3 + length4;
        
        System.out.printf("The area of the trapezoid is %.2f\n", area);
        System.out.printf("The perimeter of the trapezoid is %.2f", circumference);
        
        // close scanner
        scan.close();
        // The area of the trapezoid is 7.0
        // The perimeter of the trapezoid is 12.0644
        // כאן עליכם להמשיך... 
    } // end of method main
} //end of class Trapezoid