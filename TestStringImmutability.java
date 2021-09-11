import java.util.Date;

public class TestStringImmutability {
    public static void main(String[] args) {
        Person person = new Person("Nitish", new Address("delhi"), new Date());
        System.out.println(person.getName() + " " +
                person.getAddress().getCity() + " " +
                person.getDob());
        person.getAddress().setCity("Pune");
        System.out.println(person.getName() + " " +
                person.getAddress().getCity() + " " +
                person.getDob());
    }
}
