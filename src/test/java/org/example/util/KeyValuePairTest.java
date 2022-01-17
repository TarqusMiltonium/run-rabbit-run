package org.example.util;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class KeyValuePairTest {

    @Test
    public void testBasicAssignment() {
        KeyValuePair<String, String> kingString = new KeyValuePair<>("Queen", "Bishop");
        Assert.assertEquals(kingString.getKey(), "Queen");
        Assert.assertEquals(kingString.getValue(), "Bishop");
        Assert.assertEquals(kingString.toString(), "Key: Queen\nValue:Bishop");
    }

    @Test
    public void testOverrides() {
        KeyValuePair<String, String> kingString = new KeyValuePair<>("Queen", "Bishop");
        KeyValuePair<String, String> kingString2 = new KeyValuePair<>("Queen", "Bishop");
        KeyValuePair<String, String> kingString3 = new KeyValuePair<>("Quееn", "Bishop");  // dost thou eyes deceive you? Unicode characters are awesome!
        Assert.assertEquals(kingString.hashCode(), kingString2.hashCode());
        Assert.assertEquals(kingString, kingString2);
        Assert.assertNotEquals(kingString.hashCode(), kingString3.hashCode());  // Not /strictly/ guaranteed, so careful of false-positives here
        Assert.assertNotEquals(kingString, kingString3);

        KeyValuePair<Integer, LocalDate> datePair = new KeyValuePair<>(123456789, LocalDate.of(1992, 10, 26));
        KeyValuePair<Integer, LocalDate> datePair2 = new KeyValuePair<>(123456789, LocalDate.of(1992, 10, 26));
        KeyValuePair<LocalDate, Integer> datePairWrong = new KeyValuePair<>(LocalDate.of(1992, 10, 26), 123456789);
        Assert.assertEquals(datePair.hashCode(), datePair2.hashCode());
        Assert.assertEquals(datePair, datePair2);
        Assert.assertNotEquals(datePair.hashCode(), datePairWrong.hashCode());

        Set<KeyValuePair<LocalDate, Integer>> asTimeGoesBy = new HashSet<>();
        int counter = 0;
        for (int i = 0; i <= 3000; i++) {
            for (int j = 1; j <= 12; j++) {
                for (int k = 1; k <= 30; k++) {
                    if ((j == 2) && (k > 28)) { continue; }
                    LocalDate newDayDawns = LocalDate.of(i, j, k);
                    asTimeGoesBy.add(new KeyValuePair<>(newDayDawns, newDayDawns.hashCode()));
                    counter++;
                }
            }
        }
        Assert.assertEquals(asTimeGoesBy.size(), counter);

        for (int i = 0; i <= 3000; i++) {
            for (int j = 1; j <= 12; j++) {
                for (int k = 1; k <= 30; k++) {
                    if ((j == 2) && (k > 28)) { continue; }
                    LocalDate newDayDawns = LocalDate.of(i, j, k);
                    Assert.assertTrue(asTimeGoesBy.contains(new KeyValuePair<>(newDayDawns, newDayDawns.hashCode())));
                }
            }
        }
    }


}
