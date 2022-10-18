/**
 * @author Vu Cong Minh S25206
 */

package zad1;


import java.util.*;
import static zad1.ListCreator.collectFrom;


public class Main {
    public Main() {
        List<Integer> src1 = Arrays.asList(1, 7, 9, 11, 12);
        System.out.println(test1(src1));

        List<String> src2 = Arrays.asList("a", "zzzz", "vvvvvvv");
        System.out.println(test2(src2));

        List<Integer> src4 = Arrays.asList(
                Integer.MAX_VALUE + 1,
                Integer.MIN_VALUE + 2,
                0b10000000_00000000_00000000_00000000 + 4,
                0x80_00_00_00,
                020_000_000_000 + 5);
        System.out.println(test4(src4));
    }

    public List<Integer> test1(List<Integer> src) {
        Selector<Integer> sel = new Selector<Integer>() {
            @Override
            public boolean select(Integer obj) {
                return obj < 10;
            }
        };
        Mapper<Integer, Integer> map = new Mapper<Integer, Integer>() {
            @Override
            public Integer map(Integer obj) {
                return obj + 10;
            }
        };
        return collectFrom(src).when(sel).mapEvery(map);
    }

    public List<Integer> test2(List<String> src) {
        Selector<String> sel = new Selector<String>() {
            @Override
            public boolean select(String obj) {
                return obj.length() > 3;
            }
        };
        Mapper<String, Integer> map = new Mapper<String, Integer>() {
            @Override
            public Integer map(String obj) {
                return obj.length() + 10;
            }
        };
        return collectFrom(src).when(sel).mapEvery(map);
    }

    public List<String> test4(List<Integer> src) {
        Selector<Integer> negativeSelector = new Selector<Integer>() {
            @Override
            public boolean select(Integer obj) {
                return obj < 0;
            }
        };
        Mapper<Integer, Long> absoluteMapper = new Mapper<Integer, Long>() {
            @Override
            public Long map(Integer obj) {
                return Math.abs(obj.longValue());
            }
        };
        Selector<Long> evenSelector = new Selector<Long>() {
            @Override
            public boolean select(Long obj) {
                return obj % 2 == 0;
            }
        };
        Mapper<Long, String> reverseMapper = new Mapper<Long, String>() {
            @Override
            public String map(Long obj) {
                StringBuilder sb = new StringBuilder(obj.toString());
                return sb.reverse().toString();
            }
        };
        return collectFrom(src).when(negativeSelector).mapEvery(absoluteMapper).when(evenSelector).mapEvery(reverseMapper);
    }

    public static void main(String[] args) {
        new Main();
    }
}
