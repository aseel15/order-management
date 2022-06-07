package com.example.wholeProject.service.serviceImp;

import com.example.wholeProject.dto.CustomerDto;
import com.example.wholeProject.dto.UserDto;
import com.example.wholeProject.entities.Customer;
import com.example.wholeProject.entities.User;
import com.example.wholeProject.exception.ResourceNotFoundException;
import com.example.wholeProject.repositries.UserRepository;
import com.example.wholeProject.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;



    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("the username or password is invalid");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),getAuthority());
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> mapToDto(user)).collect(Collectors.toList());
    }



    @Override
    public UserDto getUserById(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(UserServiceImp.class.getName(), "id", id));
        return mapToDto(user);
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setName(userDto.getName());
        newUser.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        newUser.setEmail(userDto.getEmail());

        User newUser2 = userRepository.save(newUser);
        UserDto userResponse = mapToDto(newUser2);
        return userResponse;

    }

    @Override
    public UserDto updateUser(UserDto userDto, int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(UserServiceImp.class.getName(), "id", id));

       if(user != null){
           BeanUtils.copyProperties(userDto, user, "password");
           userRepository.save(user);
       }

        return userDto;

    }

    @Override
    public void deleteUser(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(UserServiceImp.class.getName(), "id", id));
        userRepository.delete(user);

    }

    @Override
    public UserDto getByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return mapToDto(user);
    }

    private UserDto mapToDto(User user) {
        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(user, userDto, "password");
       /* userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());*/

        return userDto;
    }

    private User mapToEntity(UserDto userDto) {
        User user = new User();

        BeanUtils.copyProperties(userDto, user, "password");
        /*user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());*/

        return user;
    }
}
