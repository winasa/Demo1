package com.demotruper.demo.serviceImpl;


import com.demotruper.demo.dto.UserDTO;
import com.demotruper.demo.entity.UserEntity;
import com.demotruper.demo.repository.UserRepository;
import com.demotruper.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceImpl  implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    public ServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> getUsersById(Integer id) {
        return Optional.of(userRepository.findById(id).get());
    }

    @Override
    public UserDTO updateUserEntity(UserDTO userDTO) {
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);

        Optional<UserEntity> user = userRepository.findById(userDTO.getId());
        if(user.isPresent()){
            userDTO =  modelMapper.map(userRepository.save(userEntity), UserDTO.class);

        }
        return userDTO;
    }

    @Override
    public void deleteUserById(Integer id) {

        Optional<UserEntity> userEntity = userRepository.findById(id);
        if(userEntity.isPresent()){
            userRepository.deleteById(id);
        }
    }

    @Override
    public UserEntity saveUser(UserDTO userDTO) {
        UserEntity userEntity = modelMapper.map(userDTO,UserEntity.class);
        return userRepository.save(userEntity);
    }
}
