package Controller;

import Model.Usuarios;
import Service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.JwtUtil;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;


    public AuthController (UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }
//isso esta conectado a registro criptografado via Jwt

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request){
        Usuarios usuarios = usuarioService.registrarUsuario(request.get("username"), request.get("password"));
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        Optional<Usuarios> usuarios = usuarioService.buscarPorUsername(request.get("username"));
        if (usuarios.isPresent() && usuarios.get().getPassword().equals(request.get("password"))) {
            String token = JwtUtil.generateToken(usuarios.get().getUsername());
            return ResponseEntity.ok(Map.of("token", token));
        }
        return ResponseEntity.status(401).body("Credencias invalidas");
    }
}
