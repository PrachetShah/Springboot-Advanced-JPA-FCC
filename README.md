## Advanced Springboot with relationships in JPA using Java-Maven for FCC Course of Springboot

#### Relationship Mapping

![mapping](RelationShip_Heirarchy_Mapping.png)

- Here, School is Parent of Student, and Student is Parent of StudentProfile while defining relationships

### Defining JPA Relationships

- See Student.java and StudentProfile.java to see `@OneToOne` Reltionship definition i.e Each Student has a StudentProfile

- See School.java and Student.java to see `@OneToMany` and `@ManyToOne` Relationship definition as School has one to many relationship with Students i.e School has many Students

### How to Avoid Infinite Recursion while inserting data after relationship mapping

**USING Jackson Annotations**

- School is **PARENT** of Student, so we add `@JsonManagedReference` in School.java where List of Stidents is defined which causes infinite recursion between Student and School, because student also has School defined. <br>This annotation means that Parent is responsible for serializing the child and child cannot serialize parent.

- Since Student is **CHILD** of School, we use `@JsonBackReference` annotation in Student.java where School is instantiated. This means that Student entity/object doesnt need to serialize its parent i.e School

Now using this, we need to provide a lot of overhead and makes our Student object more and more complex. To overcome this, we use `DTO` which is the **Data Transfer Object Pattern**

### DTO i.e Data Transfer Object

![dto pattern](DTO_Pattern.png)

- We want to hide sensitive information from outside world, hence we introduce a service or `mapper` which helps hide that information like password, email, phone number, etc. Using a mapper, we can have multiple representations of an entity
- So, to demonstrate DTO, we create a StudentDto Record, which contains name, email, and schoolId. We can add it into persistence DB by posting req on the url passing DTO object instead of student object
- See **Postman Collection** to undestand output of different APIs based on DTO or non-DTO. When running for **First time**, run `PostSchool/PostSchoolDto`, `PostStudent/PostSchoolDto`, and then run other commands like `Get, Delete, etc`

### SpringBoot Data Validation using spring-boot-starter-validation

- Check `StudenDto.java` for use of validation annotations.<br>

- We apply validation to the bottommost layer where we ineract with data, hence validation is applied at DTO level, to validate data before posting.
- These validations are valid with everything, even if you work with records, or simple classes
- We need to add some annotations on field of objects to apply validation on them like `@NotEmpty`
- To tell Spring which object is to be validated, use `@Valid` annotation in their defined part i.e, for `StudentDto` object, use `@Valid` annotation in `POST` req in `StudentController.java`
- We also define a `MethodArgumentNotValidException` Handler in Controller using `@ExceptionHandler` to handle the Exception class by passing in `MethodArgumentNotValidException.class` to return a custom `ResponseEntity` instead of `Error` Message in Response when validation checks fail to undesrtand what the error is in response instead of checing console
- Creating Custom Exception Handler lets us give proper custom msg, for eg for StudentPost, with not specified firstName, and improper email format we get:

```
{
    "firstName": "must not be empty",
    "email": "must be a well-formed email address"
}
```

- To write custom message in output, we can directly define a `message` parameter in annotations, for eg: `@NotEmpty(message="")`

### Types of Testing (done using spring-test)

- Unit Testing
- Integration Testing
- End-to-End Testing

### Spring-Test Introduction

- A springboot application is SpringApplicationContext
- Springboot provides `@SpringBootTest` annotation when you need Springboot features during test
  - for JUnit 4 -> `@RunWith(SpringRunner.class)` to your test
  - For JUnit 5 -> No need to add the equivalent `@ExtendWith(SpringExtension)`
- SpringBoot's auto-configuration system works well for applications but sometimes can be too much for tests
- It often helps to load only the parts of configuration that are required to test a "slice" of your application
- Each slice restricts component scan to appropriate classes and loads a very restricted set of auto-configuration class
- Keep same name as packages while testing (it can be created even without packages but it is ideal to create same package heirarchy)

### How to create test files

- Go to file you want to create test file of and click `Source Action -> Generate Tests` to create test files.
- In IDE like IntelliJ and Eclipse, you can use other shortcuts to create as well
- In test, the methods should always be `public` and by adding `@Test` annotation over the method

Some Common Annotations:

- `@BeforeEach` and `@BeforeAll` are the JUnit 5 equivalents of `@Before` and `@BeforeClass` present in JUnit 4. These annotations were renamed with clearer names to avoid confusion.
- Methods annotated with the `@BeforeEach` OR `@Before` annotation are run before each test.
- Methods annotated with the `@AfterEach` OR `@After` annotation are run after each test.
- You can initialise data in BeforeEach and delete it in AfterEach as a good practise
- `@BeforeAll` and `@AfterAll` are run before all methods of class are executed and and after all methods complete executing
- If you're certain that the tests don't make any changes to those conditions, you can use `beforeAll` (which will run once). If the tests do make changes to those conditions, then you would need to use `beforeEach`, which will run before every test, so it can reset the conditions for the next one.

Perform Tests:

- To perform tests, we can use `assert` Methods like `assertEquals, assertNotNull, and many more` to check for assertions in a `Given`, `When`, and `Assert` format like in `StudentMapperTest.java`

### Test Isolation with Mocks

- Its when we want to run tests based on isolation from its dependencies, like keeping `Repository` in isolation for `Service` tests.
- So we `MOCK` objects to run the tests. It involves creating fake objects that stand in for real objects within the system. These fake objects, known as mock objects, can be used to test how different parts of the system interact with each other in a controlled environment.
- We perform mocking in Springboot with `mockito` to perform mocking, which is like python's `unittest.mock`
- We create Mocks using the `@Mock` annotation from `org.mockito` to specify which objects to create mocks of.
- To inject mocks as dependency for a object, we use `@InjectMocks` annotation before constructor. InjectMocks will find any dependency having the `@Mock` annotation that matched the requirement. for eg, studentService will find methods with studentRepo and studentMapper
- after initialising mocks, we need to tell mockito framework to start the mocks in current class using `MockitoAnnotations.openMocks(this)` in `setUp` method in BeforeEach. `this` means that to open mocks in current class

### Layers in Application are:

![layers](Layers.png)

### Methods to Run the Code

#### To Run on Postman API

- First POST School using DTO or normal
- Second, POST Student using DTO or normal

#### 1. To run the application:

1. Install the `.jar` file from `packages` on gitub

2. After Installing use java command run the generated SNAPSHOT.jar file - `java -jar NAME_OF_FILE.jar`

#### 2. To run the code(Using Maven):

1. Install the dependencies needed to run the application and also downloading SNAPSHOT of production application - `mvn install`

2. After Installing using java command run the generated SNAPSHOT.jar file - `java -jar .\target\demo-0.0.1-SNAPSHOT.jar`

#### 3. To run the local springboot application:

1. Directly run the `DemoApplication.java` to locally run the code and to publish changes as well

### Steps for Springboot:

1. After extracting from Spring Initializr, comment JPA until repository is not implemented
2. Use `@RestController` and `@RequestMapping` for defining path in Controller File.
3. Use `@Service` to set Service file as Service Func.
4. Use `@Autowired` in both Controller and Service for Dependency Injection
5. For Table in DB, use `@Table` and `@Entity` and use necessary generators for different variables.
6. Use `@Repository` in repository file
7. in Config file, use `@Configuration` on class and `@Bean` for method to add initial values in DB when running command
8. If we use `spring.jpa.hibernate.ddl-auto=create-drop`, then there is need for Config, else use, `...=update`, to dont delete the DB Details when application reloads
9. To create a custom banner in springboot while booting, create banner.txt file in main/resources

### Some Concepts

1. The central artifacts in Spring's Java configuration support are `@Configuration` -annotated classes and `@Bean` -annotated methods. The `@Bean` annotation is used to indicate that a method instantiates, configures, and initializes a new object to be managed by the Spring IoC container.
2. `Spring's Inversion of Control (IoC)` principle flips the script. With IoC, you delegate the responsibility of object creation and dependency management to the Spring container. Instead of creating objects explicitly, you define their dependencies through configuration files or annotations.

### To Upload Package on GitHub:

1. Change the REPOSITORY on `/{user}/.m2/settings.xml` and add `OWNER` and `GITHUB_TOKEN`
2. Add `Distrbution Management` in `pom.xml`
