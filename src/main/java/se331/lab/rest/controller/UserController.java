package se331.lab.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se331.lab.rest.security.entity.User;
import se331.lab.rest.security.repository.UserRepository;
import se331.lab.rest.service.UserService;
import se331.lab.rest.util.LabMapper;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @PostMapping("/registers")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) == null ) {
            User output = userService.save(user);
            return ResponseEntity.ok(LabMapper.INSTANCE.getUserDTO(output));
        }else {
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.BAD_GATEWAY);
        }
    }
}
