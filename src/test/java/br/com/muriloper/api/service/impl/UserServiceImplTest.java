package br.com.muriloper.api.service.impl;

import br.com.muriloper.api.domain.UserLogin;
import br.com.muriloper.api.domain.dto.UserDTO;
import br.com.muriloper.api.repository.UserRepository;
import br.com.muriloper.api.service.exception.DataIntegrityViolationException;
import br.com.muriloper.api.service.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static br.com.muriloper.api.Utils.ApiMessages.EMAIL_EXISTS;
import static br.com.muriloper.api.Utils.ApiMessages.OBJECT_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "Murilo Pereira";
    public static final String EMAIL = "murilo@email.com";
    public static final String PASSWORD = "123";
    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private UserLogin user;

    private UserDTO userDTO;

    private Optional<UserLogin> optionalUserLogin;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        //Instancia as dependencias da classe testada
        startClass();
    }

    private void startClass() {
        user = new UserLogin(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUserLogin = Optional.of(new UserLogin(ID, NAME, EMAIL, PASSWORD));
    }

    @Test
    void whenFindByIdThenReturnAnUserLoginInstance() {
        when(repository.findById(anyInt())).thenReturn(optionalUserLogin);

        UserLogin response = service.findById(ID);

        assertNotNull(response);
        assertEquals(UserLogin.class, response.getClass());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException(){
        when(repository.findById(anyInt()))
                .thenThrow(new ObjectNotFoundException(OBJECT_NOT_FOUND.getMensagem()));

        try{
            service.findById(ID);
        } catch (Exception e) {
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals(OBJECT_NOT_FOUND.getMensagem(), e.getMessage());
        }
    }

    @Test
    void whenCreateThenReturnAnUserLoginInstance() {
        when(repository.save(any())).thenReturn(user);

        UserLogin response = service.create(userDTO);

        assertNotNull(response);
        assertEquals(UserLogin.class, response.getClass());
    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationException(){
        when(repository.findByEmail(anyString())).thenReturn(optionalUserLogin);
        try {
            service.create(userDTO);
        } catch(Exception e){
            assertEquals(DataIntegrityViolationException.class, e.getClass());
            assertEquals(EMAIL_EXISTS.getMensagem(), e.getMessage());
        }

    }

    @Test
    void whenFindByEmailThenReturnAnUserLoginInstance(){
        when(repository.findByEmail(anyString())).thenReturn(optionalUserLogin);

        Optional<UserLogin> response = service.findByEmail(EMAIL);

        assertNotNull(response);
        assertEquals(Optional.class, response.getClass());
    }

    /*
    @Test
    void whenFindByEmailThenReturnAnDataIntegrityViolationException(){
        when(repository.findByEmail(anyString()))
                .thenThrow(new DataIntegrityViolationException(OBJECT_NOT_FOUND.getMensagem()));

        try {
            service.findByEmail(EMAIL);
        } catch (Exception  e){
            assertEquals(DataIntegrityViolationException.class, e.getClass());
            assertEquals(OBJECT_NOT_FOUND.getMensagem(), e.getMessage());
        }
    }*/

    @Test
    void whenUpdateThenReturnAnUserLoginInstance() {
        when(repository.save(any())).thenReturn(user);
        when(repository.findByEmail(anyString())).thenReturn(optionalUserLogin);

        UserLogin response = service.update(userDTO);

        assertNotNull(response);
        assertEquals(UserLogin.class, response.getClass());
    }

    @Test
    void whenUpdateThenReturnAnDataIntegrityViolationException(){
        when(repository.findByEmail(anyString()))
                .thenThrow(new DataIntegrityViolationException(OBJECT_NOT_FOUND.getMensagem()));

        try {
            service.update(userDTO);
        } catch (Exception e) {
            assertEquals(DataIntegrityViolationException.class, e.getClass());
            assertEquals(OBJECT_NOT_FOUND.getMensagem(), e.getMessage());
        }
    }

    @Test
    void whenDeleteWithSuccess() {
        when(repository.findById(anyInt())).thenReturn(optionalUserLogin);
        doNothing().when(repository).deleteById(anyInt());
        
        service.delete(ID);
        
        verify(repository, times(1)).deleteById(anyInt());
    }

    @Test
    void whenDeleteThenReturnObjectNotFoundException(){
        doNothing().when(repository).deleteById(anyInt());
        try {
            service.delete(ID);
        } catch(Exception e) {
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals(OBJECT_NOT_FOUND.getMensagem(), e.getMessage());
        }
    }
}