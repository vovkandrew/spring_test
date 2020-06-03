import config.AppConfig;
import model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.UserService;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        User bob = new User("Bob", "bob@gmail.com");
        User alice = new User("Alice", "alice@hotmail.com");
        User george = new User("George", "george@yahoo.com");
        User jeniffer = new User("Jeniffer", "jeniffer@aol.com");
        User vasya = new User("Vasiliy", "vasiliy@mail.ru");

        UserService userService = context.getBean(UserService.class);

        userService.add(bob);
        userService.add(alice);
        userService.add(george);
        userService.add(jeniffer);
        userService.add(vasya);

        for (User u: userService.listUsers()) {
            System.out.println(u.toString());
        }
    }
}
