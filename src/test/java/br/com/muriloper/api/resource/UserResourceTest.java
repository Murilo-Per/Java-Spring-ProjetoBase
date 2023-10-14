package br.com.muriloper.api.resource;

import br.com.muriloper.api.domain.UserLogin;
import br.com.muriloper.api.domain.dto.UserDTO;
import br.com.muriloper.api.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserResourceTest {

    public static final Integer ID = 1;
    public static final String NAME = "Murilo Pereira";
    public static final String EMAIL = "murilo@email.com";
    public static final String PASSWORD = "123";

    @InjectMocks
    UserResource resource;

    @Mock
    UserServiceImpl service;

    @Mock
    ModelMapper mapper;
    private UserLogin user;
    private UserDTO userDTO;


    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        //Instancia as dependencias da classe testada
        startClass();
    }

    private void startClass() {
        user = new UserLogin(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(service.findById(Mockito.anyInt())).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = resource.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}