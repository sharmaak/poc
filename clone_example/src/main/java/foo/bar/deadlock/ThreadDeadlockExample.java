package foo.bar.deadlock;

public class ThreadDeadlockExample {

    // Read more: http://javarevisited.blogspot.in/2010/10/what-is-deadlock-in-java-how-to-fix-it.html

    public void method1(){
        synchronized(String.class){
            System.out.println("Acquired lock on String.class object");

            synchronized (Integer.class) {
                System.out.println("Acquired lock on Integer.class object");
            }
        }
    }

    public void method2(){
        synchronized(Integer.class){
            System.out.println("Acquired lock on Integer.class object");

            synchronized (String.class) {
                System.out.println("Acquired lock on String.class object");
            }
        }
    }
}
