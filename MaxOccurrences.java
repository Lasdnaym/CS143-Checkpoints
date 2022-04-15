import java.util.*;

public class MaxOccurrences {
    public static int maxOccurrences(List<Integer> list) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int value = 0;
        for (int i : list) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
            if (map.get(i) > value) {
                value = map.get(i);
            }
        }
        return value;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(maxOccurrences(list));
    }
}
