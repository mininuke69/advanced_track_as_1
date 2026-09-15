import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads a list of numbers, and can reconstruct the corresponding list of Palindromes,
 * produce the size of the largest magic set, and the content of that magic set.
 * 
 * Usage:
 * TODO:
 * task 1:
 * 1. create function Boolean palindromeCheck(long numberToCheck)
 *    that returns true if input is palindrome
 * 2. create function long palindromeCorrect(long numberToCorrect)
 *    that corrects the input and returns palindrome
 *    it does this by increasing the number until it becomes a palindrome
 * 3. run this function for each element in input list
 *
 * task 2:
 * 1. create function magicSetCheck(...) that returns true if input is a magic set
 * 2. create function int magicSetGenerate(...) that takes the corrected palindrome list
 *    and returns the number of elements in the largest magic set.
 *    it does this by trying combinations of two palindromes, if it finds one,
 *    it tries to make one of three, up to the length of the corrected palindrome list.
 *    if it fails to make a list anytime before that, it returns.
 * 3. parse input and run magicSetGenerate(...)
 *
 * magicSetGenerate:
 * 1. Separate each element to according to its length in a HashMap
 * 2. Start from the lowest length element and check if it is in a magic set of any longer length element.
 * 3. If a magic set is found continue from the longer length element.
 * 4. If no magic set is found return to the previous length object.
 * 
 * Task 3:
 * 1. Modify the magicSetGenerate and magicSetLength function so it would return a list instead of int.
 *    a. A list will be stored in the function (also recursive) and replaced each time a longer function is found. 
 *    b. If task 3 is selected then it would return the list and print it.
 *    c. If task 2 is selected then length of the list would be printed.
 * 2. Make sure that last magic set element from the list would be returned if more than 1 magic sets are possible.
 *    (Which should be simple as the loop can be used to replace the stored list when longer or equal length list is found.)
 * 
 * END TODO
 * 
 * @author Nikita Ponomarev
 * @ID 2461463
 * @author Huseyin Basharan
 * @ID 2428482
 * 
 */
class KingsPalindromeList {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int taskNumber = input.nextInt();
        int numberOfElements = input.nextInt();
        
        long[] numbers = new long[numberOfElements];
        
        for (int i = 0; i < numberOfElements; i++) {
            numbers[i] = input.nextLong();
            numbers[i] = palindromeCorrect(numbers[i]);
        }
        
        switch (taskNumber) {
            case 1 -> {
                System.out.print(numbers[0]);
                for (int i = 1; i < numberOfElements; i++) {
                    System.out.print(" " + numbers[i]);
                }
            }
            case 2 -> {
                System.out.print(magicSetGenerate(numbers).size());
            }
            case 3 -> {
                ArrayList<Long> list = magicSetGenerate(numbers);
                System.out.print(list.get(0));
                for (int i = 1; i < list.size(); i++) {
                    System.out.print(" " + list.get(i));
                }
            }
            default -> {
                return;
            }
        }
        
        System.out.println();
    }
    
    //Takes number, reverses it and checks if the two match
    static boolean palindromeCheck(long numberToCheck) {
        String numberString = Long.toString(numberToCheck);
        String reverseNumberString = new StringBuilder(numberString).reverse().toString();
        return numberString.equals(reverseNumberString);
    }

    //Finds the nearest greatest palindrome
    static long palindromeCorrect(long numberToCorrect) {
        while (!palindromeCheck(numberToCorrect)) {
            numberToCorrect++;
        }
        return numberToCorrect;
    }

    static boolean magicSetCheck(long num1, long num2) {
        
        var e0 = String.valueOf(num1);
        var e1 = String.valueOf(num2);

        // match length by truncating longest, then check equality
        var lengthDifference = Math.abs(e0.length() - e1.length());
        var charactersToRemoveOnEachSide = lengthDifference / 2;
        var el0IsLonger = e0.length() > e1.length();
        var longestElement = el0IsLonger ? e0 : e1;
        var shortestElement = el0IsLonger ? e1 : e0;
        var truncatedLongest = longestElement.substring(
                charactersToRemoveOnEachSide,
                longestElement.length() - charactersToRemoveOnEachSide
        );
            
        return truncatedLongest.equals(shortestElement);
    }
    
    static ArrayList<Long> magicSetGenerate(long[] numbersList) {
        // uncorrupt the input
        var uncorruptedNumbersList = new ArrayList<Long>();
        for (long number : numbersList) {
            uncorruptedNumbersList.add(palindromeCorrect(number));
        }
        //Create an array that stores longs with different lengths
        ArrayList<ArrayList<Long>> lengthArray = new ArrayList<ArrayList<Long>>();
        for (int i = 0; i < 9; i++) {
            lengthArray.add(new ArrayList<Long>());
        }
        for (long number : uncorruptedNumbersList) {
            lengthArray.get(Long.toString(number).length() / 2).add(number);
        }
        
        //Remove empty arrays
        lengthArray.removeIf(x -> x.size() == 0);
        
        ArrayList<Long> longestSet = new ArrayList<Long>();
        
        //Search for every length of magic set starting from the smallest
        for (int i = 0; i < lengthArray.size(); i++) {
            for (long numberSearched : lengthArray.get(i)) {
                ArrayList<Long> searchedSet = new ArrayList<Long>();
                searchedSet.add(numberSearched);
                
                searchedSet = magicSetLength(lengthArray, i, searchedSet);
                
                //Replace the longest set if a longer or equal size set with greater X is found
                longestSet = searchedSet.size() >= longestSet.size() ? searchedSet : longestSet;
                
                //If maximum possible size is found, return
                if (longestSet.size() == lengthArray.size()) {
                    break;
                }
            }
        }

        return longestSet;
    }
    
    static ArrayList<Long> magicSetLength(ArrayList<ArrayList<Long>> list, int startIndex, ArrayList<Long> initialSet) {
        long numberSearched = initialSet.getLast();
        ArrayList<Long> longestSet = new ArrayList<Long>(initialSet);
        
        //Checks each of the longer number group 
        for (int i = startIndex + 1; i < list.size(); i++) {
            for (long largerNumber : list.get(i)) {
                if (magicSetCheck(numberSearched, largerNumber)) {
                    //Create a new list to be searched that also contains the longer number
                    ArrayList<Long> searchedSet = new ArrayList<Long>(initialSet);
                    searchedSet.add(largerNumber);
                    
                    //Search for any longer magic set
                    searchedSet = magicSetLength(list, i, searchedSet);
                    
                    //Replace the longest set if a longer or equal size set with greater X is found
                    longestSet = searchedSet.size() >= longestSet.size() ? searchedSet : longestSet;
                }
            }
        }
        
        return longestSet;
    }
}