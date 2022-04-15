import java.util.*;

public class MakeChange {
    public static void main(String[] args) {
        List<Integer> coinValues = new LinkedList<Integer>();
        coinValues.add(1);
        coinValues.add(5);
        coinValues.add(10);
        coinValues.add(25);

        System.out.println(" P  N  D  Q");
        System.out.println("------------");
        makeChange(28, coinValues);
    }

    // Public private pair.
    public static void makeChange(int amount, List<Integer> coinValues) {
        makeHelp(amount, coinValues, new int[4], 0);
    }

    // amount and coinValues come from the problem statement.
    // account: used to store how much of each coin we have - storing combonations.
    // coinType: used to keep track of which coin we are on. 
    private static void makeHelp(int amount, List<Integer> coinValues, int[] account, int coinType){
        // If we run out of coin types
        if (coinType == coinValues.size()) {
            // If we are at the amount print our solution.
            if (amount == 0) {
                System.out.println(Arrays.toString(account));
            }
            return;
        }
        // If the amount of money remaining is greater than the coin value at a certain coinType.
        if (amount >= coinValues.get(coinType)) {
            // Divide the total amount by the coin type so we know how much is the maximum amount of that
            // type of coin we can have. If amount = $2 and type is quarters, we know we can have at max 
            // 8 quarters. 
            int temp = amount / coinValues.get(coinType);
            // Since we know the max, consider all cases up until the max.
            for (int i = 0; i <= temp; i++) {
                // Set the amount of a certain type to the case we are considering.
                account[coinType] = i;
                // Recursive call.
                // amount - i * coinValues.get(coinType): the new amount should be the current amount minus
                // the total amount of however many of the coin type we are currently on.
                // coinValues: list of values.
                // account: used to store how much of each coin we have - storing combonations.
                // coinType + 1: go to the next coin type.
                makeHelp(amount - i * coinValues.get(coinType), coinValues, account, coinType + 1);
            }
        // If the remaining amount is no longer large enough to exceed the coin value than we need to
        // recurse to the next coin value. If the amount is 20 cents and the current coin type is quarter 
        // which is 25 cents, then we should just move onto the next coin type since there is no reason to 
        // consider quarters anymore.
        } else {
            // Since we are moving on from the current coin type, we should record that we have 0 of that coin
            // type in our combonation.
            account[coinType] = 0;
            // We want to make a recursive call moving on to the next coin type.
            makeHelp(amount, coinValues, account, coinType + 1);
        }
    }

}
