import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Employee {
    private String name;
    private Integer age;

    Employee(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.name = age;
    }

    @Override
    public String toString() {
        return this.name + " : " + this.age + " ";
    }


}

public class Java8FeatureTest {
    public static void main(String[] args) {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Nitish", 27));
        employeeList.add(new Employee("Nitish", 25));
        employeeList.add(new Employee("Sumit", 30));
        employeeList.add(new Employee("Amit", 27));
        employeeList.add(new Employee("Rahul", 19));
        employeeList.add(new Employee("Shivam", 17));
        employeeList.add(new Employee("Mahesh", 45));
        employeeList.add(new Employee("Mahesh", 45));
        employeeList.add(new Employee("Ramesh", 20));
        //doFilterTestWithPredicates(employeeList);
        //doOptionalOrElseThrowTest(employeeList);
        //doCollectionCopyTest(employeeList);
        //doStringJoinTest();
        //doDistinctTest(employeeList);
        //doComparingTest(employeeList);
        doStreamApiTest(employeeList);
    }

    private static void doStreamApiTest(final List<Employee> employeeList) {
        System.out.println("====== Stream API findFirst and findAny test ======");
        System.out.println(employeeList.stream().findFirst());
        System.out.println(employeeList.stream().findAny());
        //Use limit to terminate infinite condition, as we can increase stream by incrementing its size.
        testInfiniteStream(employeeList);
        //Test parallel stream
        testParallelStream(employeeList);
        System.out.println(employeeList.stream().distinct().count());
        testMatchInStream(employeeList);
        testStreamGenerateMethod();
    }

    //We Can use generate function of stream to create infinite stream, so must need to use limit hear to stop out
    //of memory exception. It uses supplier to generate infinite stream.
    //We can create infinite stream using Iterator also. Stream.iterator
    private static void testStreamGenerateMethod() {
        System.out.println("Testing generate function of stream using limit");
        Stream<String> stringStream = Stream.generate(() -> "Hello infinite stream").limit(5);
        stringStream.forEach(System.out::println);
    }

    private static void testMatchInStream(final List<Employee> employeeList) {
        System.out.println("====== Test match function in stream ======");
        System.out.println(employeeList.stream().anyMatch(e -> e.getName().equals("Nitish")));
        System.out.println(employeeList.stream().allMatch(e -> e.getName().equals("Nitish")));
        System.out.println(employeeList.stream().noneMatch(e -> e.getName().equals("Nitish")));
    }

    private static void testParallelStream(final List<Employee> employeeList) {
        //It will do the print task in parallel for each element in stream.
        System.out.println("====== Test parallel stream ======");
        employeeList.parallelStream().forEach(e -> System.out.println(e.getName()));
    }

    private static void testInfiniteStream(final List<Employee> employeeList) {
        System.out.println("====== Test infinite stream using limit function ======");
        employeeList.stream().limit(2).forEach(System.out::println);
        //Let's say that we want to create an infinite stream of random UUIDs.
        //The first step to achieving this using Stream API is to create a Supplier of those random values:
        Supplier<UUID> randomUUIDSupplier = UUID::randomUUID;
        //When we define a supplier we can create an infinite stream using a generate() method:
        Stream<UUID> infiniteStreamOfRandomUUID = Stream.generate(randomUUIDSupplier);
        List<UUID> randomInts = infiniteStreamOfRandomUUID
                .skip(10)
                .limit(10)
                .collect(Collectors.toList());
        System.out.println(randomInts);
    }

    private static void doComparingTest(final List<Employee> employeeList) {
        System.out.println("====== Test comparator in java 8 using sort function ======");
        employeeList.add(null);
        employeeList.add(null);
        employeeList.sort(Comparator.nullsFirst(Comparator.comparing(Employee::getName)).thenComparing(Employee::getName)
                .thenComparing(Comparator.comparingInt(Employee::getAge).reversed()));
        System.out.println(employeeList);
    }

    private static void doDistinctTest(final List<Employee> employeeList) {
        System.out.println("====== Test distinct function in stream ======");
        System.out.println(employeeList.size());
        List<Employee> output = employeeList.stream().distinct().collect(Collectors.toList());
        System.out.println(output.size());
        System.out.println(output);
    }

    private static void doStringJoinTest() {
        System.out.println("====== Test String join class in java8 ======");
        final StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
        stringJoiner.add("cat");
        stringJoiner.add("dog");
        stringJoiner.add("rat");
        System.out.println(stringJoiner);
        final StringJoiner stringJoiner2 = new StringJoiner("-");
        stringJoiner2.add("hello");
        stringJoiner2.add("world");
        stringJoiner2.add("animals");
        System.out.println(stringJoiner.merge(stringJoiner2));
    }

    private static void doCollectionCopyTest(final List<Employee> employeeList) {
        System.out.println("====== Test collection copy test ======");
        final List<Employee> employeeListNew = new ArrayList<>();
        employeeListNew.add(new Employee("Raman", 21));
        employeeListNew.add(new Employee("Suman", 33));
        //This will overwrite the dest element from top
        //In this case we can't take employeeListNew as dest bcz src size is greater, it will give compile
        //time error.
        //Collections.copy(employeeList, employeeListNew);
        employeeListNew.addAll(employeeList);
        employeeListNew.forEach(employee -> System.out.println(employee.getName() + " : " + employee.getAge()));
    }

    private static void doOptionalOrElseThrowTest(final List<Employee> employeeList) {
        System.out.println("====== Test optional ofNullable ans elseThrow ======");
        Optional.ofNullable(employeeList).orElseThrow(RuntimeException::new);
    }

    private static void doFilterTestWithPredicates(final List<Employee> employeeList) {
        System.out.println("====== Test filter test in stream also test using predicate ======");
        final Predicate<Employee> employeePredicate = employee -> employee.getName().startsWith("S");
        final Predicate<Employee> employeePredicate1 = employee -> employee.getAge() > 20;
        final List<Employee> byNameAndByAge = employeeList.stream()
                .filter(employee -> employee.getName().startsWith("S")
                        && employee.getAge() > 20).collect(Collectors.toList());
        final List<Employee> byNameAndByAgeWithPredicate = employeeList.stream()
                .filter(employeePredicate.and(employeePredicate1)).collect(Collectors.toList());
        byNameAndByAge.forEach(e -> System.out.println(e.getName() + " : " + e.getAge()));
        byNameAndByAgeWithPredicate.forEach(e -> System.out.println(e.getName() + " : " + e.getAge()));
    }


}
