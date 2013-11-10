package foo.bar.cloning;

public class IAmCloneable implements Cloneable {

    int x;
    Long y;
    String z;


    public IAmCloneable(int x, Long y, String z) {
        this.x=x;
        this.y=y;
        this.z=z;
    }


    // Using Object.clone(), it is a shallow clone implemented by
    // Object class.
    @Override
    public IAmCloneable clone() throws CloneNotSupportedException {

        // The method clone for class Object performs a specific
        // cloning operation. First, if the class of this object
        // does not implement the interface Cloneable, then a
        // CloneNotSupportedException is thrown. Note that all
        // arrays are considered to implement the interface Cloneable.
        // Otherwise, this method creates a new instance of the
        // class of this object and initializes all its fields
        // with exactly the contents of the corresponding fields
        // of this object, as if by assignment; the contents of
        // the fields are not themselves cloned. Thus, this method
        // performs a "shallow copy" of this object, not a
        // "deep copy" operation.
        return (IAmCloneable)super.clone();
    }
}
