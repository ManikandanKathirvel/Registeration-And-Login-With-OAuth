package com.mani.RegisterationAndLogin.Service;
import com.mani.RegisterationAndLogin.Entity.User;
import com.mani.RegisterationAndLogin.GlobalException.AuthenticationException;
import com.mani.RegisterationAndLogin.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    public final UserRepository userrepo;
    @Autowired
    PasswordEncoder  encoder;

    public UserService(UserRepository userrepo) {
        this.userrepo = userrepo;
    }
    public User register(User user){

        if(user.getUsername()==null ||user.getPassword()==null) {
            return null;
        }
        else{
            User users=new User();
            users.setUsername(user.getUsername());
            users.setEmail(user.getEmail());
            users.setPassword(encoder.encode(user.getPassword()));
            return userrepo.save(users);
        }
    }
    public boolean isUserAlreadyRegistered(String email) {
        long count =userrepo.getEmailCount(email);
        return count > 0;
    }
    public User auth( String email,String password) throws AuthenticationException {
        User user=userrepo.findByEmail(email).orElseThrow();
        if (!encoder.matches(password, user.getPassword())) {
            throw new AuthenticationException("Invalid email or password");

        }
        return user;
    }

}