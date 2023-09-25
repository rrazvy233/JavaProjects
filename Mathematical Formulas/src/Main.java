/* 1. get input and convert negative numbers to positive
 * 2. forward the input to the methods and use the formulas
 * to calculate base/surface area and volume
 * 3. display the radius, height and results for the area and volume
 * 4. get input for fractions
 * 5. calculate fractions and give error if denominator is 0
 * 6. display fractions
 * Razvan Petcu
 */
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    private static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args)
    {
        //area and volume calculation
        System.out.println("-----------------");
        System.out.println("Area and volume ");
        System.out.println("Insert an even number of numbers and end with q ");
        System.out.print("(do: 1 2 3 4 q, dont: 1 2 3 q) \n");
        System.out.println("The first number will be taken as the radius and second as height");
        System.out.println("-----------------");
        ArrayList<Integer> numbers = input();
        int arraySize = numbers.size();
        int radNum = 0;
        int highNum = 0;
        if(arraySize >= 2){
            highNum = 1;
        }
        if (highNum < arraySize && arraySize == 1){
            System.out.print("Insert an even number of numbers (do: 1 2 3 4 q, dont: 1 2 3 q) \n");
            numbers = input();
        } else {
            //loop to get and set the numbers from the array index
            while(highNum < arraySize && arraySize != 1){
                int rad = numbers.get(radNum);
                int high = numbers.get(highNum);
                //this reads the size of the array and moves the values to next
                if (arraySize > 1 && arraySize % 2 == 0){
                    radNum = radNum +2;
                    highNum = highNum +2;
                    //if items in array is not even asks for user to input again
                } else if (arraySize > 1 && arraySize % 2 != 0){
                    System.out.print("Insert an even number of numbers (do: 1 2 3 4 q, dont: 1 2 3 q) \n");
                    numbers = input();
                }
                System.out.println("\n r = " + rad + " h = " + high);
                area(rad);
                area(rad, high);
                volume(rad, high);
            }
        }
        //fraction part
        System.out.println("-----------------");
        System.out.println("Fractional method ");
        System.out.println("-----------------");
        ArrayList<Integer> fractions = input();
        int fractionSize = fractions.size();
        int numerator = 0;
        int denominator = 0;
        if(fractionSize >= 1){
            denominator = 1;
        }
        if (denominator < fractionSize && fractionSize == 1){
            System.out.print("Insert an even number of numbers (do: 1 2 3 4 q, dont: 1 2 3 q) \n");
            fractions = input();
        } else {
            while (denominator <= fractionSize){
                int num = fractions.get(numerator);
                int denom = fractions.get(denominator);
                if (fractionSize > 1 && fractionSize % 2 == 0){
                    numerator = numerator +2;
                    denominator = denominator +2;
                } else if (fractionSize > 1 && fractionSize % 2 != 0){
                    System.out.print("Insert an even number of numbers (do: 1 2 3 4 q, dont: 1 2 3 q) \n");
                    fractions = input();
                }
                System.out.print(num + "/" + denom + " = ");
                int[] toPrint = fraction(num, denom);
                printFraction(toPrint);
            }
        }
    }
    public static ArrayList<Integer> input(){
        //reads input
        userInput.useDelimiter("[*\\s]+");
        ArrayList<Integer> inputNumbers = new ArrayList<Integer>();
        String check = ""; //runs items through here to detect q
        while(!check.equals("q")){
            if(userInput.hasNextInt()){
                inputNumbers.add(userInput.nextInt());
            }else {
                check = userInput.next();
            }
        }
        //for loop to convert negative numbers to positive
        for(int i = 0; i< inputNumbers.size(); i++){
            if(inputNumbers.get(i) < 0){
                int num = inputNumbers.get(i);
                num = Math.abs(inputNumbers.get(i));
                num = inputNumbers.set(i, num);
            }
        }
        return inputNumbers;
    }

    public static double area(int radius)
    {
        //base area
        double pi = 3.14;
        double baseArea = pi * Math.pow(radius, 2.00);
        System.out.println("Base area: " + baseArea);
        return baseArea;
    }

    public static double pythagoras(int sideA, int sideB){
        //pythagoras to get necessary the element in  the surface area formula
        double hypotenuse;
        hypotenuse = Math.sqrt((sideA*sideA)+(sideB*sideB));
        hypotenuse = Math.round(hypotenuse * 100.0)/100.0;
        return hypotenuse;
    }
    public static double area(int radius, int height){
        //surface area formula
        double pi = 3.14;
        double pyth = pythagoras(radius, height);
        double surfaceArea;
        surfaceArea = pi * radius * pyth;
        surfaceArea = Math.round(surfaceArea * 100.0)/100.0;
        System.out.print("Surface area: " + surfaceArea);
        return surfaceArea;

    }
    public static double volume(int radius, int height){
        double pi = 3.14;
        double volume;
        volume = (pi * (radius*radius)* height) / 3;
        volume = Math.round(volume *100.0)/100.0;
        System.out.println("\nVolume: " + volume);
        return volume;
    }
    public static int[] fraction(int numerator, int denominator){
        int fractions[] = new int[3];
        if(denominator != 0){
            int first;
            first = numerator / denominator;
            int third;
            third = gcd(numerator, denominator);
            int second;
            second = numerator - third * first;
            fractions[0] = first;
            fractions[1] = second;
            fractions[2] = third;
        }
        else if (denominator == 0){
            System.out.print("Error \n");
        }
        return fractions;
    }
    public static int gcd (int a, int b){
        //find greatest common denominator
        if (a < b){
            a = b;
            b = a;
        }
        if (b != 0){
            int c = a % b;
            a = b;
            b = c;
        }
        return a;
    }
    public static int[] printFraction (int[] parts){
        //prints the fractions
        int number = parts[0];
        int numerator = parts[1];
        int denominator = parts[2];
        if (number > 0 && numerator > 0 && denominator > 0){
            System.out.println(number + " " + numerator + "/" + denominator);
        }

        else if(number == 0 && numerator > 0 && denominator > 0){
            System.out.println(numerator + "/" + denominator);
        }
        else if(number > 0 && numerator == 0 && denominator > 0){
            System.out.println(number);
        }

        return parts;
    }
}