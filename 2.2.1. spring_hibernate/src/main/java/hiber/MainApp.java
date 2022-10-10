package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User u = new User("John", "Smith", "smith_john@mail.com");
      u.setUserCar(new Car("BMV", 375));
      userService.add(u);
      u = new User("Brad", "White", "white_bred@mail.com");
      u.setUserCar(new Car("UAZ", 666));
      userService.add(u);
      u = new User("Matt", "Adams", "adams_matt@mail.com");
      u.setUserCar(new Car("Honda", 482));
      userService.add(u);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("\nId = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Cars_id = "+user.getUserCar());
      }

      System.out.println();
      userService.getCarUser("BMV", 375);

      context.close();
   }
}
