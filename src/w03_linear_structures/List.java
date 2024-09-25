package w03_linear_structures;

public interface List<T> {
    // Insert a value at a specific index (index starts from zero)
    // If the index is invalid, return false
    // Return true in other case
    boolean insertAt(int index, T value);

    // Insert a value before another value
    // If there are multiple searchValue, the first one (from the left) is used
    // If searchValue doesn't exist, return false
    // Return true in other cases
    boolean insertBefore(T searchValue, T value);

    // Insert a value after another value
    // If there are multiple searchValue, the first one (from the left) is used
    // If searchValue doesn't exist, return false
    // Return true in other cases
    boolean insertAfter(T searchValue, T value);

    // Remove a value at a specific index (index starts from zero)
    // If the index is invalid, return false
    // Return true in other cases
    boolean removeAt(int index);

    // Remove a value in the list
    // If there are multiple value, remove the first one (from the left)
    // If value doesn't exist, return false
    // Return true in other cases
    boolean remove(T value);

    // Return whether a value exist in the list
    boolean contains(T value);

    // Return the number of elements in the list
    int size();

    // Return whether the next value exist in the list
    boolean hasNext();

    // Return the next value in the list, and advance to the next index
    // If there is no value available, return null
    T next();

    // Reset the iteration
    void reset();

    // Return a value at a specific index
    // Return null if index is invalid
    T get(int index);
}
