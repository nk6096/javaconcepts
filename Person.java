import java.util.Date;

public final class Person {
    private final String name;
    private final Date dob;
    private final Address address;

    public Person(String name, Address address, Date dob) {
        this.name = name;
        this.address = address;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return new Address(address.getCity());
    }

    public Date getDob() {
        return new Date(dob.getTime());
    }
}