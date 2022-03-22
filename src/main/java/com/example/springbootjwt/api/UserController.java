package com.example.springbootjwt.api;

import com.example.springbootjwt.business.UserService;
import com.example.springbootjwt.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody User user){
        userService.add(user);
        return ResponseEntity.ok("Eklendi");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam int userId){
        userService.delete(userId);
        return ResponseEntity.ok("Silindi");
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody User user){
        userService.update(user);
        return ResponseEntity.ok("Güncellendi");
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/getbyid")
    public ResponseEntity<?> getById(@RequestParam int userId){
        return ResponseEntity.ok(userService.getById(userId));
    }
}
