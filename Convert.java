    import java.util.*;
import java.lang.*;

public class Convert {
    public static Map<String, Set<String>> convert(Set<String> numbers) {
        Map<String, Set<String>> result = new HashMap<>();
		for (String number : numbers) {
			String dootLmak = number.substring(0, 3);
            String bonkGang = number.substring(4,8);
			if (!result.containsKey(dootLmak)) {
				result.put(dootLmak, new HashSet<>());
			}
			result.get(dootLmak).add(bonkGang);
		}
		return result;
    }

    public static void main(String[] args) {
        Set<String> numbers = Set.of(
            "493-3923", "723-9278", "384-1917", "555-1795", "384-4923", 
            "555-4923", "555-1212", "723-9823"
        );
        System.out.print(convert(numbers));
    }
}
