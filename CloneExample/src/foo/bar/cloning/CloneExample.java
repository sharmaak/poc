package foo.bar.cloning;

public class CloneExample {

    public static void main(String ... args) {
        IAmCloneable clnbl = new IAmCloneable(10, 100L, "Foo");
        IAmCloneable clnblClone = null;
        try{
            clnblClone=clnbl.clone();
            System.out.println("IAmCloneable: clone successful");
        } catch (CloneNotSupportedException x) {
            x.printStackTrace();
        }

        System.out.println(clnbl.x == clnblClone.x);
        // The next two statements provide proof
        // that Object.clone does a shallow clone
        // since y and z are objects and not primitives
        System.out.println(clnbl.y==clnblClone.y);
        System.out.println(clnbl.z==clnblClone.z);

        IAmNotCloneable nclbl = new IAmNotCloneable("");
        try{
            nclbl.clone();
        } catch (CloneNotSupportedException x){
            System.out.println("IAmNotCloneable: Clone failed");
        }
    }
}
