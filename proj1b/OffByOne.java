/**
 * @author LM
 * @create 2019-08-14 11:02
 */
public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return diff == 1 || diff == -1;
    }
}
