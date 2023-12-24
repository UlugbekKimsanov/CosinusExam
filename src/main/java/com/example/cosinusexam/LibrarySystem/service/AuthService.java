package com.example.cosinusexam.LibrarySystem.service;
import com.example.cosinusexam.LibrarySystem.config.jwt.JwtService;
import com.example.cosinusexam.LibrarySystem.dto.Request.LoginDTO;
import com.example.cosinusexam.LibrarySystem.dto.Request.ResetPasswordDTO;
import com.example.cosinusexam.LibrarySystem.dto.Request.UserCr;
import com.example.cosinusexam.LibrarySystem.dto.Response.TokenDTO;
import com.example.cosinusexam.LibrarySystem.entity.UnverifiedUser;
import com.example.cosinusexam.LibrarySystem.entity.UserEntity;
import com.example.cosinusexam.LibrarySystem.entity.VerificationData;
import com.example.cosinusexam.LibrarySystem.entity.enums.UserRole;
import com.example.cosinusexam.LibrarySystem.entity.enums.UserStatus;
import com.example.cosinusexam.LibrarySystem.exception.DataNotFoundException;
import com.example.cosinusexam.LibrarySystem.exception.DuplicateValueException;
import com.example.cosinusexam.LibrarySystem.repository.UnverifiedUserRepository;
import com.example.cosinusexam.LibrarySystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final MailSenderService mailSenderService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UnverifiedUserRepository unverifiedUserRepository;
    private Map<String, VerificationData> verificationCodes = new HashMap<>();

    public String create(UserCr userCr) {
        checkEmailUnique(userCr.getEmail());
        checkPasswordIsValid(userCr.getPassword());
        UnverifiedUser user = modelMapper.map(userCr, UnverifiedUser.class);
        user.setPassword(userCr.getPassword());
        VerificationData verifyData = generateVerificationData();
        user.setVerificationCode(verifyData.getVerificationCode());
        user.setVerificationDate(verifyData.getVerificationDate());
        String message = mailSenderService.sendVerificationCode(user.getEmail(),verifyData.getVerificationCode());
        unverifiedUserRepository.save(user);
        System.out.println("message = " + message);
        return message;
    }


    @SneakyThrows
    public TokenDTO verify(String email, String verificationCode) {
        UnverifiedUser user = unverifiedUserRepository.findByEmail(email);
        VerificationData verificationData = new VerificationData(user.getVerificationCode(),user.getVerificationDate());
        if (checkVerificationCodeAndExpiration(verificationData, verificationCode))
            throw new BadRequestException("Verification code wrong");
        UserEntity userEntity = modelMapper.map(user,UserEntity.class);
        userEntity.setRole(UserRole.USER);
        userEntity.setStatus(UserStatus.ACTIVE);
        userRepository.save(userEntity);
        unverifiedUserRepository.delete(user);
        return jwtService.generateToken(userEntity.getEmail());
    }



    public String newVerifyCode(String email) {
        UnverifiedUser user = unverifiedUserRepository.findByEmail(email);
        if(Objects.equals(user,null)){
            throw new DataNotFoundException("You do not sign up yet!");
        }
        VerificationData verifyData = generateVerificationData();
        user.setVerificationCode(verifyData.getVerificationCode());
        user.setVerificationDate(verifyData.getVerificationDate());
        String message = mailSenderService.sendVerificationCode(user.getEmail(),verifyData.getVerificationCode());
        unverifiedUserRepository.save(user);
        return message;
    }


    @SneakyThrows
    public TokenDTO login(LoginDTO loginDTO) {
        UserEntity user = (UserEntity) userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new DataNotFoundException("User not found!"));
        if(Objects.equals(user.getPassword(),loginDTO.getPassword())){
            return jwtService.generateToken(user.getEmail());
        }else {
            throw  new BadRequestException("Password or email incorrect!");
        }

    }

    public String forgotPassword(String email) {
        VerificationData verificationData = generateVerificationData();
        verificationCodes.put(email,verificationData);
        return mailSenderService.sendVerificationCode(email,verificationData.getVerificationCode());
    }

    public String resetPassword(ResetPasswordDTO resetPasswordDTO) {
        VerificationData verificationData = verificationCodes.get(resetPasswordDTO.getEmail());
        if (checkVerificationCodeAndExpiration(verificationData, resetPasswordDTO.getVerificationCode()))
            return "Verification code wrong";
        checkPasswordIsValid(resetPasswordDTO.getNewPassword());
        UserEntity userEntity = (UserEntity) userRepository.findByEmail(resetPasswordDTO.getEmail()).get();
        userEntity.setPassword(resetPasswordDTO.getNewPassword());
        userRepository.save(userEntity);
        return "Password successfully changed";
    }


    private UserEntity getUserByID(UUID userID) {
        return userRepository.findById(userID).orElseThrow(
                () -> new DataNotFoundException("User not found with ID: " + userID)
        );
    }


    private void checkEmailUnique(String email) {
        if (userRepository.existsByEmail(email))
            throw new DuplicateValueException("User already exists with Email: " + email);
    }



    public void checkPasswordIsValid(String password) {
        String passwordRegex = "^(?=.*[a-zA-Z])(?=.*\\d).{8,20}$";
        if (!password.matches(passwordRegex)) {
            throw new IllegalArgumentException("Invalid password: " + password);
        }
    }


    private VerificationData generateVerificationData() {
        Random random = new Random();
        String verificationCode = String.valueOf(random.nextInt(100000, 1000000));
        return new VerificationData(verificationCode, LocalDateTime.now());
    }


    @SneakyThrows
    public boolean checkVerificationCodeAndExpiration(VerificationData verificationData, String verificationCode) {
        if (!verificationData.getVerificationDate().plusMinutes(100).isAfter(LocalDateTime.now()))
            throw new BadRequestException("Verification code expired");
        return !Objects.equals(verificationData.getVerificationCode(), verificationCode);
    }

}

