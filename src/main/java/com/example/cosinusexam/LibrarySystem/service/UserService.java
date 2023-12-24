package com.example.cosinusexam.LibrarySystem.service;

import com.example.cosinusexam.LibrarySystem.dto.Request.UserCr;
import com.example.cosinusexam.LibrarySystem.dto.Response.UserResponseDTO;
import com.example.cosinusexam.LibrarySystem.entity.UserEntity;
import com.example.cosinusexam.LibrarySystem.exception.DataNotFoundException;
import com.example.cosinusexam.LibrarySystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.config.provisioning.UserDetailsManagerResourceFactoryBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserResponseDTO myProfile(Principal principal){
        System.out.println("principal.getName() = " + principal);
        UserEntity userEntity = (UserEntity) userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new DataNotFoundException("User not found"));
        return modelMapper.map(userEntity, UserResponseDTO.class);
    }
    public UserResponseDTO update(UserCr user, Principal principal) {
        UserEntity userEntity1 = userRepository.findById(UUID.fromString(principal.getName())).
                orElseThrow(() -> new DataNotFoundException("User not found!"));
        if(!Objects.equals(user.getName(),null)){
            userEntity1.setName(user.getName());
        }if(!Objects.equals(user.getSurname(),null)){
            userEntity1.setSurname(user.getSurname());
        }if(!Objects.equals(user.getEmail(),null)){
            userEntity1.setEmail(user.getEmail());
        }
        UserEntity userEntity = userRepository.save(userEntity1);
        return modelMapper.map(userEntity,UserResponseDTO.class);
    }

    public String delete(Principal principal) {
        userRepository.deleteById(UUID.fromString(principal.getName()));
        return "Deleted!";
    }
}
