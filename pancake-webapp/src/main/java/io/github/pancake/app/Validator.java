package io.github.pancake.app;

/**
 * Validation interface for arbitrary object.
 * @author Bence_Kornis
 *
 * @param <T> type of object to be validated
 */
public interface Validator<T> {
    /**
     * Validation method
     * @param object the object to be validated
     * @return boolean which indicates if the validation succeeded
     */
    public boolean validate(T object);
}
