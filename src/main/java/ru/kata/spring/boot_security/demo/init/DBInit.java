/*
package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DBInit {
    private UserServiceImpl userService;
    private RoleServiceImpl roleService;

    @Autowired
    public DBInit(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void run() {
        Role adminRole = new Role("ADMIN");
        Role userRole = new Role("USER");

        roleService.addRole(adminRole);
        roleService.addRole(userRole);

        userService.addUser(new User("Luka", "Modric", 1985,
                "lukam@rmcf.com", "$2a$12$dr5y9Is2wyv6CbjvDNnOiOQEKRcbxtIMJpePoA69xX/89S41MdMG.", List.of(adminRole)));

        userService.addUser(new User("Marcelo", "Vieira", 1988,
                "marcelo@rmcf.com", "$2a$12$/h3XkINV1cE3vN0QoWRQF.Ir5jZVk02DSUKogesMScz4bPYSWFZiW", List.of(userRole)));

        userService.addUser(new User("Karim", "Benzema", 1987,
                "kb9@rmcf.com", "$2a$12$aA1POprEiftCx1hLqfX7WOBk/GXySpVoYpWJrZXT0E9JqJ2iXYgTe", List.of(userRole)));

        userService.addUser(new User("Sergio", "Ramos", 1986,
                "sergior@rmcf.com", "$2a$12$Hn8k63jCwVG25Dio.cSUleDDEYf2G695kMx3SSaEyFRwYhB/IvfLC", List.of(adminRole)));
    }
}
*/
