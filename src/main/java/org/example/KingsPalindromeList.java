/**
 * Reads a list of numbers, and can reconstruct the corresponding list of Palindromes,
 * produce the size of the largest magic set, and the content of that magic set.
 * 
 * Usage:
 * TODO:
 * 1. create function boolean PalindromeCheck(long NumberToCheck) that returns true if input is palindrome
 * 2. create function long PalindromeCorrect(long NumberToCorrect) that corrects the input and returns palindrome
 *    it does this by increasing the number until it becomes a palindrome
 * 3. for task 1: run this function for each element in input list
 *
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
        //Test
        System.out.println(palindromeCheck(123454321));
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
}