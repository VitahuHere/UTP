/**
 * @author Vu Cong Minh S25206
 */

package UTP9.zad1;

import java.math.BigDecimal;
import java.math.MathContext;

@FunctionalInterface
public interface ParamsInterface {
    BigDecimal apply(BigDecimal a, BigDecimal b, MathContext mathContext);
}
