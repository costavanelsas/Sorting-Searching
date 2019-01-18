package nl.hva.ict.ss.textsearch;

/**
 * @author Costa van Elsas
 * I was inspired by the sedgewick book to write this code
 */
public class BackwardsSearch {

    private int[] right;
    private String pat;
    private int comparisons;
    private int Radix;

    /**
     * Returns index of the right most location where <code>needle</code> occurs within <code>haystack</code>. Searching
     * starts at the right end side of the text (<code>haystack</code>) and proceeds to the first character (left side).
     * @param needle the text to search for.
     * @param haystack the text which might contain the <code>needle</code>.
     * @return -1 if the <code>needle</code> is not found and otherwise the left most index of the first
     * character of the <code>needle</code>.
     */
    int findLocation(String needle, String haystack) {

        this.Radix = 256;
        this.pat = needle;
        right = new int[Radix];

        for (int c = 0; c < Radix; c++)
            right[c] = -1;
        for (int j = needle.length() - 1; j >= 0; j--)
            right[needle.charAt(j)] = j;

        int H = haystack.length();
        int N = needle.length();
        int skip;
        this.comparisons = 0;

        for (int i = H - N; i > 0; i -= skip)
        {
            skip = 0;

            for (int j = 0; j < N - 1; j++){
                this.comparisons++;

                if (needle.charAt(j) != haystack.charAt(i+j))
                {
                    skip = Math.max(1, j - right[haystack.charAt(i+j)]);
                    break;
                }
            }
            if (skip == 0) return i; // found.
            System.out.println("Amount of comparisons: "+ this.comparisons);
        }
        return -1; // not found.
    }

    /**
     * Returns the number of character compares that where performed during the last search.
     * @return the number of character comparisons during the last search.
     */
    int getComparisonsForLastSearch(int compares) {
        return this.comparisons;
    }
}
