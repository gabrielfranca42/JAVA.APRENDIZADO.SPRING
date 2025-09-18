package Repository;

import Model.Usuarios;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import pratica.aprendizado.spring.pratica.aprendiazdo.spring.Application;

import javax.swing.text.html.parser.Entity;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test")
@ContextConfiguration(classes = Application.class)
class UsuarioRepositoryTest {


    @Autowired
    UsuarioRepository usuarioRepository;


    @Autowired
    EntityManager entityManager;


    @Test
    @DisplayName("Should get User successfully from DB")
    void findUserByUsernameCase1(){
        Usuarios usuarios = new Usuarios("fernado","xdxd");
    }


    private Usuarios createUsername(Usuarios username){
        Usuarios usuarios = new Usuarios();
        this.entityManager.persist(usuarios);
        this.entityManager.flush();

        Optional<Usuarios> result = usuarioRepository.findByUsername("fernado");

        assertTrue(result.isPresent());
        assertEquals("fernado", result.get().getUsername());

        return usuarios;
    }

}