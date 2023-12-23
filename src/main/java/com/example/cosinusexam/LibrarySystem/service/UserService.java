package com.example.cosinusexam.LibrarySystem.service;

import com.example.cosinusexam.LibrarySystem.config.jwt.JwtService;
import com.example.cosinusexam.LibrarySystem.dto.Request.AuthDto;
import com.example.cosinusexam.LibrarySystem.dto.Request.UserCr;
import com.example.cosinusexam.LibrarySystem.dto.Response.JwtResponse;
import com.example.cosinusexam.LibrarySystem.entity.UserEntity;
import com.example.cosinusexam.LibrarySystem.exception.DataNotFoundException;
import com.example.cosinusexam.LibrarySystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;
//    public JwtResponse signIn(AuthDto dto) {
//        UserEntity user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new DataNotFoundException("user not found"));
//        if (dto.getPassword().equals(user.getPassword())) {
//            return new JwtResponse(jwtService.generateToken(user));
//        }
//        throw new AuthenticationCredentialsNotFoundException("password didn't match");
//    }
    public UserEntity create(UserCr userCr){
        return userRepository.save(modelMapper.map(userCr,UserEntity.class));
    }
    public UserEntity myProfile(Principal principal){
        return userRepository.findById(UUID.fromString(principal.getName()))
                .orElseThrow(() -> new DataNotFoundException("User not found"));
    }
    public UserEntity update(UserCr user, Principal principal) {
        UserEntity userEntity1 = userRepository.findById(UUID.fromString(principal.getName())).
                orElseThrow(() -> new DataNotFoundException("User not found!"));
        if(!Objects.equals(user.getName(),null)){
            userEntity1.setName(user.getName());
        }if(!Objects.equals(user.getSurname(),null)){
            userEntity1.setSurname(user.getSurname());
        }if(!Objects.equals(user.getEmail(),null)){
            userEntity1.setEmail(user.getEmail());
        }if(!Objects.equals(user.getPassword(),null)){
            userEntity1.setPassword(user.getPassword());
        }
        return userRepository.save(userEntity1);
    }

    public String delete(Principal principal) {
        userRepository.deleteById(UUID.fromString(principal.getName()));
        return "Deleted!";
    }

    public JwtResponse signIn(AuthDto dto) {
        UserEntity user = (UserEntity) userRepository.findByEmail(dto.getEmail()).
                orElseThrow(() -> new DataNotFoundException("User not found"));
        if (dto.getPassword().equals(user.getPassword())) {
            return new JwtResponse(jwtService.generateToken(user));
        }
        throw new AuthenticationCredentialsNotFoundException("Incorrect password!");
    }
}
