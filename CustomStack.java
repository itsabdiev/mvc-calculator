/**
 * A custom implementation of a stack data structure
 *
 * @author Team Decorator
 */
public class CustomStack <D> {
    private Object[] elements;
    private int elementsCount;

    /**
     * Constructs an empty CustomStack with initial size of 10
     */
    public CustomStack() {
        this.elements = new Object[10];
    }

    /**
     * Checks if the stack is empty
     *
     * @return {@code true} if the stack is empty, {@code false} otherwise
     */
    public boolean isEmpty() {
        return elementsCount == 0;
    }

    /**
     * Pushes an element onto the stack
     *
     * @param element The element to push onto the stack
     * @return The element that was pushed
     */
    public D push(D element) {
        guaranteeCapacity(elementsCount+1);
        elements[elementsCount++] = element;
        return element;
    }

    /**
     * Pops and removes the top element from the stack
     *
     * @return The top element of the stack
     * @throws RuntimeException if the stack is empty
     */
    public D pop() {
        D object = peek();
        removeElement(elementsCount-1);
        return object;
    }

    /**
     * Removes an element at specified index
     *
     * @param index The index of element that will be removed
     */
    private void removeElement(int index) {
        for (int i = index; i < elementsCount - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[elementsCount - 1] = null;
        elementsCount--;
    }

    /**
     * Peeks at the top element of the stack without removing it
     *
     * @return The top element of the stack
     * @throws RuntimeException if the stack is empty
     */

    @SuppressWarnings("unchecked")
    public D peek() {
        if (elementsCount == 0) throw new RuntimeException("Stack is empty");
        return (D) elements[elementsCount-1];
    }

    /**
     * If there is not an empty place to push, it will increase the size of the array, (will not) otherwise
     *
     * @param minCapacity The minCapacity(minSize) that must be guaranteed
     */
    private void guaranteeCapacity(int minCapacity) {
        if (!(elements.length - minCapacity > 0)) {
            increaseStackSize();
        }
    }

    /**
     * It will increase size of array by doubling the previous size
     */
    private void increaseStackSize() {
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity * 2 ;
        elements = copyElements(elements,newCapacity);
    }

    /**
     * It will copy elements of previous array to new created array
     *
     * @param elements The previous array
     * @param newCapacity The new size of array
     * @return New array with elements of the previous array
     */
    private Object[] copyElements(Object[] elements, int newCapacity) {
        Object[] newElements = new Object[newCapacity];
        for (int i = 0; i < elements.length; i++) {
            newElements[i] =  elements[i];
        }
        return newElements;
    }

    /**
     * String representation of Custom object
     *
     * @return String that contains each element of stack
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i <= elementsCount - 1; i++) {
            sb.append(elements[i]);
            if (i < elementsCount - 1) {
                sb.append(", ");
            }
        }
        return sb.append("]").toString();
    }
}