package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        Car hyundai = new Car("Hyundai", 2008);
        Car lada = new Car("Lada", 2109);
        Car uaz = new Car("UAZ", 2022);
        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        user1.setCar(hyundai);
        user2.setCar(lada);
        user3.setCar(uaz);
        hyundai.setUser(user1);
        lada.setUser(user2);
        uaz.setUser(user3);
        userService.add(user1);
        userService.add(user2);
        userService.add(user3);

        //Выводит всех пользователей с машинами.
        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
        }
        // Выводит пользователя по марке и серии машины или выводит сообщение если пользователя с такой машиной нет.
        System.out.println(userService.getUserByCar("hyundai", 2008));
        System.out.println(userService.getUserByCar("lamba", 2005));
        context.close();
    }
}
