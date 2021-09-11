@FunctionalInterface
public interface MyFunctionalInterface {
    void myAbstractMethod();

    default void myDefaultMethod1() {
        System.out.println("My myDefaultMethod1 interface test");
    }

    default void myDefaultMethod2() {
        System.out.println("My myDefaultMethod2 interface test");
    }

    static void myStaticMethod1() {
        System.out.println("My myStaticMethod1 interface test");
    }

    static void myStaticMethod2() {
        System.out.println("My myStaticMethod2 interface test");
    }

    static void main(String[] args) {
        //TODO : need to get clarification on below calls.
        MyFunctionalInterface myFunctionalInterface = MyFunctionalInterface::myStaticMethod1;
        myFunctionalInterface.myDefaultMethod1();
        myFunctionalInterface.myDefaultMethod2();
        MyFunctionalInterface.myStaticMethod1();
        MyFunctionalInterface.myStaticMethod2();
    }
}
