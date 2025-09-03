package Controller;


import Service.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//exemplo aqui de um controlador REST com essas informações do REST vc tem as requisições web

@RestController
@RequestMapping("/api")
public class Controller {

   private final Service service;

   public Controller(Service service) {
       this.service = service;
   }

   @GetMapping("/teste")
    public String mensagem() {
       return service.obterService();
   }

}
