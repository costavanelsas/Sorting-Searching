package nl.hva.ict.ss.textsearch;

/**
 * @author Costa van Elsas
 */
public class BackwardsSearch {


    private int[] right;
    private String pat;
    private int compares = 0;

    /**
     * Constructor
     */
    public BackwardsSearch(String pat) {
        this.pat = pat;
        int M = pat.length();
        int R = 256;
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1; // -1 for chars not in pattern
        for (int j = 0; j < M; j++) // rightmost position for
            right[pat.charAt(j)] = j;
    }

    /**
     * Returns index of the right most location where <code>needle</code> occurs within <code>haystack</code>. Searching
     * starts at the right end side of the text (<code>haystack</code>) and proceeds to the first character (left side).
     * @param needle the text to search for.
     * @param haystack the text which might contain the <code>needle</code>.
     * @return -1 if the <code>needle</code> is not found and otherwise the left most index of the first
     * character of the <code>needle</code>.
     */
    int findLocation(String needle, String haystack) {

        int N = haystack.length();
        int M = needle.length();
        int skip;
        int amountOfCompares = 0;

        for (int i = 0; i <= N-M; i += skip)
        { // Does the pattern match the text at position i ?
            amountOfCompares++;
            skip = 0;
            for (int j = M-1; j >= 0; j--)
                if (needle.charAt(j) != haystack.charAt(i+j))
                {

                    skip = j - right[haystack.charAt(i+j)];
                    if (skip < 1) skip = 1;
                    break;
                }

            System.out.println("Amount of compares " + amountOfCompares);
            amountOfCompares += compares;
            if (skip == 0) return i; // found.
        }


        return -1; // not found.
    }

    /**
     * Returns the number of character compares that where performed during the last search.
     * @return the number of character comparisons during the last search.
     */
    int getComparisonsForLastSearch(int compares) {
        return compares;
    }

}
