package pratica.aprendizado.spring.pratica.aprendiazdo.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

//como esse codigo e pra estudo irei lotar ele de informações
// ao fazer um api rest podemos criar varios tipos de arquitetura mas uma muito utilizada que podemos usar e mvc
//que divide seu codigo em camadas que podem ser resumidas como
//"Controller" = aqui ficas os controladores REST
//"Repository" = aqui fica tabelas de banco de dados e seu acesso
//"Service" = aqui deve ficar as regras de negocio
//"Model" = e aqui o modelo dos dados
//

@SpringBootApplication
//ao ser feito o model que vc quer que seja feito deploy use esse exemplo de baixo fazendo a devida referencia a entidade
@EntityScan({"model", "usuarios"})

public class  Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	System.out.println("hello world se essa budega commita xdxdxdxd");
    }


}
