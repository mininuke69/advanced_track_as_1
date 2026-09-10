/**
 * Reads a list of numbers, and can reconstruct the corresponding list of Palindromes,
 * produce the size of the largest magic set, and the content of that magic set.
 * 
 * Usage:
 * TODO:
 * task 1:
 * 1. create function boolean palindromeCheck(long numberToCheck) that returns true if input is palindrome
 * 2. create function long palindromeCorrect(long numberToCorrect) that corrects the input and returns palindrome
 *    it does this by increasing the number until it becomes a palindrome
 * 3. run this function for each element in input list
 *
 * task 2:
 * 1. create function magicSetCheck(...) that returns true if input is a magic set
 * 2. create function magicSetGenerate(...) that takes the corrected palindrome list and returns the largest magic set.
 *    it does this by trying combinations of two palindromes, if it finds one,
 *    it tries to make one of three, up to the length of the corrected palindrome list.
 *    if it fails to make a list anytime before that, it returns that list.
 * 3. run magicSetGenerate(...) and count the number of outputs
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