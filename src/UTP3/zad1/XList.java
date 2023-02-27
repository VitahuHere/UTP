/**
 * @author Vu Cong Minh S25206
 */

package UTP3.zad1;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class XList<T> extends ArrayList<T> {
    public XList(T... elements) {
        super(Arrays.asList(elements));
    }

    public XList(Collection<T> elements) {
        super(elements);
    }

    public static <T> XList<T> of(T... elements) {
        return new XList<>(elements);
    }

    public static <T> XList<T> of(Collection<T> elements) {
        return new XList<>(elements);
    }

    public static XList<String> charsOf(String s) {
        XList<String> list = new XList<>();
        for (int i = 0; i < s.length(); i++) {
            list.add(s.substring(i, i + 1));
        }
        return list;
    }

    public static XList<String> tokensOf(String s, String delim) {
        return new XList<>(Arrays.asList(s.split(delim)));
    }

    public static XList<String> tokensOf(String s) {
        return tokensOf(s, " ");
    }

    public XList<T> union(Collection<T> c) {
        XList<T> list = new XList<>(this);
        list.addAll(c);
        return list;
    }

    public XList<T> union(T... elements) {
        return union(Arrays.asList(elements));
    }

    public XList<T> diff(Collection<T> c) {
        XList<T> list = new XList<>(this);
        list.removeAll(c);
        return list;
    }

    public XList<T> unique() {
        LinkedHashSet<T> set = new LinkedHashSet<>(this);
        return new XList<>(set);
    }

    private <U> XList<XList<U>> combine(List<XList<U>> src){
        if(src.size() == 0){
            XList<XList<U>> list = new XList<>();
            list.add(new XList<U>());
            return list;
        }
        XList<XList<U>> result = new XList<>();
        for(U element : src.get(src.size() - 1)){
            XList<XList<U>> temp = combine(src.subList(0, src.size() - 1));
            for(XList<U> el : temp){
                el.add(element);
            }
            result.addAll(temp);
        }
        return result;
    }

    public <U> XList<XList<U>> combine(){
        XList<XList<U>> src = new XList<>();
        for(List<U> t : (List<List<U>>) this){
            src.add(XList.of(t));
        }
        return this.combine(src);
    }

    public <U> XList<U> collect(Function<T, U> f){
        XList<U> list = new XList<>();
        for(T t : this){
            list.add(f.apply(t));
        }
        return list;
    }

    public String join(String delim){
        StringBuilder sb = new StringBuilder();
        for(T t : this){
            sb.append(t.toString());
            sb.append(delim);
        }
        sb.delete(sb.length() - delim.length(), sb.length());
        return sb.toString();
    }

    public String join(){
        return join("");
    }

    public void forEachWithIndex(BiConsumer<T, Integer> f){
        for(int i = 0; i < this.size(); i++){
            f.accept(this.get(i), i);
        }
    }
}
