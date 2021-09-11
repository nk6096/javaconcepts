class A {
    public void m1() {
        System.out.println("m1 is called parent");
    }

    public void m2() {
        System.out.println("m2 is called parent");
    }
}

class B extends A {
    public void m1() {
        System.out.println("m1 is called from child");
    }

    public void m3() {
        System.out.println("m3 is called child");
    }
}

public class JavaInheritanceTest {
    public static void main(String[] args) {
        B b = new B();
        b.m1();
        b.m2();
        b.m3();
        A a = new B();
        a.m1();
        a.m2();
        A a1 = new A();
        a1.m1();
        a1.m2();
        ///B b1 = new A(); -> this does not work as it's compile time error.
    }
}
