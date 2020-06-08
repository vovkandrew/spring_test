package controllers;

import dto.UserResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/inject")
    public String injectUsers() {
        User bob = new User("Bob", "bob@gmail.com");
        User alice = new User("Alice", "alice@hotmail.com");
        User george = new User("George", "george@yahoo.com");
        User jeniffer = new User("Jeniffer", "jeniffer@aol.com");

        userService.add(bob);
        userService.add(alice);
        userService.add(george);
        userService.add(jeniffer);

        return "4 users have been injected into database";
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return getUserResponseDto(userService.getUserById(userId));
    }

    @GetMapping("/")
    public List<UserResponseDto> getAl() {
        return userService.listUsers().stream()
                .map(this::getUserResponseDto)
                .collect(Collectors.toList());
    }

    private UserResponseDto getUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setName(user.getName());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }
}
