public class JavaThreadExample {
    public static void main(String[] args) {
        final Runnable myRunnable = () -> System.out.println("Called run method of thread class");
        final Thread myThread = new Thread(myRunnable);
        myThread.start();

        final MyFunctionalInterface myFunctionalInterface = () -> System.out.println("implemented functional interface in Thread Example");
        myFunctionalInterface.myAbstractMethod();
    }
}

