
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Reads a list of numbers, and can reconstruct the corresponding list of Palindromes,
 * produce the size of the largest magic set, and the content of that magic set.
 * 
 * Usage:
 * TODO:
 * task 1:
 * 1. create function boolean palindromeCheck(long numberToCheck)
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
 * 1. Seperate each element to according to its length in a HashMap
 * 2. Start from the lowest length element and check if it is in a magic set of any longer length element.
 * 3. If a magic set is found continue from the longer length element.
 * 4. If no magic set is found return to the previous length object.
 * 
 * END TODO
 * 
 * @author <NAME STUDENT 1>
 * @ID <ID STUDENT 1>
 * @author <NAME STUDENT 2>
 * @ID <ID STUDENT 2>
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
        }
        
        switch (taskNumber) {
            case 1 -> {
                for (int i = 0; i < numberOfElements; i++) {
                    numbers[i] = palindromeCorrect(numbers[i]);
                    System.out.print(numbers[i] + " ");
                }
                
            }
            default -> {
                return;
            }
        }
    }
    
    //Takes number, reverses it and checks if the two match
    static boolean palindromeCheck(long numberToCheck) {
        String numberString = Long.toString(numberToCheck);
        String reverseNumberString = new StringBuilder(numberString).reverse().toString();
        return numberString.equals(reverseNumberString);
    }

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
    
    static int magicSetGenerate(long[] numbersList) {
        HashMap<Integer, ArrayList<Long>> lengthMap = new HashMap<Integer, ArrayList<Long>>();
        for (int i = 1; i <= 17; i += 2) {
            lengthMap.put(i, new ArrayList<Long>());
        }
        
        for (long number : numbersList) {
            lengthMap.get(Long.toString(number).length()).add(number);
        }
        
        magicSetLength(lengthMap, 1);
        
        return 1;
    }
    
    static int magicSetLength(HashMap<Integer, ArrayList<Long>> list, int startLength) {
        for (long smallerNumber : list.get(startLength)) {
            for (int i = startLength; i <= 17; i++) {
                for (long largerNumber : list.get(i)) {
                    if (magicSetCheck(smallerNumber, largerNumber)) {
                        return magicSetLength(list, i) + 1;
                    }
                }
            }
        }
        return 0;
    }
}