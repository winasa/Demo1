package com.demotruper.demo.jwt;

import com.demotruper.demo.dto.LoginRequest;
import com.demotruper.demo.entity.Rol;
import com.demotruper.demo.entity.UserEntity;
import com.demotruper.demo.repository.RolRepository;
import com.demotruper.demo.repository.UserRepository;
import com.demotruper.demo.service.JwtAuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private ModelMapper modelMapper;

    public ResponseEntity<?> login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        UserEntity user=userRepository.findByNombre(request.getUsername());
        String token=jwtService.getToken(modelMapper.map(user.getUser(), UserDetails.class));

        return ResponseEntity.ok(new JwtAuthenticationResponse(token));


    }

    public AuthResponse register(RegisterRequest request) {
        UserEntity user = UserEntity.builder()
            .nombre(request.getUsername())
            .password(passwordEncoder.encode( request.getPassword()))
            .build();

        userRepository.save(user);
       Rol rol= rolRepository.save(Rol.builder().id(null).nombre(request.getUsername()).build());
        return AuthResponse.builder()
            .token(jwtService.getToken(modelMapper.map(user,UserDetails.class)))
            .build();
        
    }

}
