package main.java.biggest.purchaser;

import main.java.largest.sub.array.LongestSubArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by LethalLima on 10/20/16.
 *
 * Challenge: Sort Biggest Purchaser
 * Goal: Given input of purchasers with cost of purchase,
 * output purchasers with most expensive cost first and tie breakers
 * sorted by name of purchaser.
 *
 * For example:
 * jeff $10
 * mario $9
 * michelle $15
 * mario $6
 *
 * should output:
 * mario $15
 * michelle $15
 * jeff $10
 */
public class BiggestPurchasersSorted {
    // expected delimiter between purchaser and cost
    private final String textPattern = "\\s\\$";
    private Pattern pattern;
    private List<Map.Entry<String, Integer>> purchasersList;

    BiggestPurchasersSorted(Scanner scanner) {
        // build pattern expected
        pattern = Pattern.compile(textPattern);

        sortPurchasesFromInput(scanner);
    }

    private void sortPurchasesFromInput(Scanner scanner) {
        Map<String, Integer> map = new HashMap<>();
        String[] purchase;
        String purchaser;
        int cost;

        while(scanner.hasNextLine()) {
            purchase = pattern.split(scanner.nextLine().trim());
            purchaser = purchase[0].toLowerCase();
            cost = Integer.parseInt(purchase[1]);

            // if purchaser already exists as a key, update cost, else
            // add purchaser with cost
            if(map.containsKey(purchaser)) {
                map.put(purchaser, map.get(purchaser) + cost);
            } else {
                map.put(purchaser, cost);
            }
        }

        purchasersList = new ArrayList<>(map.entrySet());

        Collections.sort(purchasersList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                int value = o1.getValue().compareTo(o2.getValue());

                if(value == 0) {
                    return o1.getKey().compareTo(o2.getKey());
                }

                return -value;
            }
        });
    }

    public List<Map.Entry<String, Integer>> getPurchasersList() {
        return purchasersList;
    }

    public static void main(String[] args) throws FileNotFoundException {
        URL filePath = LongestSubArray.class.getResource("../../../../resources/BiggestPurchasersSorted.txt");

        if(filePath != null) {
            Scanner scan = new Scanner(new File(filePath.getFile()));

            BiggestPurchasersSorted biggestPurchasersSorted = new BiggestPurchasersSorted(scan);
            scan.close();

            for(Map.Entry<String, Integer> purchase: biggestPurchasersSorted.getPurchasersList()) {
                System.out.println(purchase.getKey() + " $" + purchase.getValue());
            }

        } else {
            System.out.println("Could not find file.");
        }
    }
}
