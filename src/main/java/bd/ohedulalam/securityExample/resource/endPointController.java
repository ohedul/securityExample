package bd.ohedulalam.securityExample.resource;


import bd.ohedulalam.securityExample.model.User;
import bd.ohedulalam.securityExample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "rest/")
public class endPointController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/all")
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @PostMapping(value = "/save")
    public User save(@RequestBody User user){
        return userRepository.save(user);
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping(value = "/get/one/{user_id}")
    public User getUserById(@PathVariable(value = "user_id") final int user_id){
        User user = userRepository.findByUserId(user_id);
        if(user == null){
            return null;
        }

        return user;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/get/update/{user_id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "user_id") final int userId, @RequestBody final User userDetail){
        User user = userRepository.findByUserId(userId);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        user.setUserId(userDetail.getUserId());
        user.setF_name(userDetail.getF_name());
        user.setL_name(userDetail.getL_name());
        user.setEmail(userDetail.getEmail());
        user.setPassword(userDetail.getPassword());
        user.setRoles(userDetail.getRoles());

        User update = userRepository.save(user);
        return ResponseEntity.ok().body(update);

    }

}
