package Service;
//
@org.springframework.stereotype.Service
public class Service {

    private final Service service;

    public Service(Service service){
        this.service = service;
    }

    public String obterService(){
        return service.obterService();

    }
}

