public class EmployeeHashAndEquals {
    private String name;
    private Integer age;

    public EmployeeHashAndEquals(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeHashAndEquals)) return false;
        EmployeeHashAndEquals employeeHashAndEquals = (EmployeeHashAndEquals) o;
        return name.equals(employeeHashAndEquals.getName()) && age.equals(employeeHashAndEquals.getAge());
    }

    @Override
    public int hashCode() {
        if (this.name == null || this.age == null)
            return 0;
        int result = 31;
        result *= this.name.hashCode();
        result *= this.age.hashCode();
        return result;
    }

    public static void main(String[] args) {
        EmployeeHashAndEquals emp = new EmployeeHashAndEquals("Akshay", 24);
        EmployeeHashAndEquals emp1 = new EmployeeHashAndEquals("Akshay", 24);

        String s1 = "abc";
        String s2 = "abc";
        String s3 = new String("abc");

        System.out.println(s1 == s2); //true
        System.out.println(s1 == s3); //false
        System.out.println(s1.equals(s3)); //true
        System.out.println(s2.equals(s3)); //true

        System.out.println(emp.equals(emp1));
        System.out.println(emp.hashCode());
        System.out.println(emp1.hashCode());

    }
}
