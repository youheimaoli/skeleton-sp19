/**
 * @author LM
 * @create 2019-08-14 11:10
 */
public class OffByN implements CharacterComparator {
    private int offset;
    public OffByN(int N) {
        offset = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return diff == offset || diff == -offset;
    }
}
