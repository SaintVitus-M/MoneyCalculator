package software.ulpgc.money.architecture.model;

/**
 * Represents a currency with its code and name.
 *
 * @param code
 * @param name
 *
 * @author      Vít Mikula
 * @version     1.0, 31/12/2024
 * @since       1.0
 */
public record Currency(String code, String name) {

    /**
     * Returns a string representation of this currency.
     *
     * @return a string representation of the currency.
     * @since       1.0
     */
    @Override
    public String toString() {
        return code + "-" + name;
    }

    /**
     * Checks if this currency is equal to another object.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     * @since       1.0
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Currency) {
            return code.equals(((Currency) obj).code);
        } else {
            return false;
        }
    }
}
