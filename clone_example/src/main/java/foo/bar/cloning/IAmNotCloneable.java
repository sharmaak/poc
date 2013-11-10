package foo.bar.cloning;

public class IAmNotCloneable {

    String name;

    public IAmNotCloneable(String name){
        this.name=name;
    }


    // Even though this class has an appropriate clone() method,
    // this method will throw CloneNotSupportedException because
    // the class does not implement the Cloneable interface
    public IAmNotCloneable clone() throws CloneNotSupportedException {
        return (IAmNotCloneable)super.clone();
    }

}
