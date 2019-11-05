package co.com.runt.servicios;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class InicioAppController {

	@GetMapping
	public String sayHello() {
		return "<center><h2>Bienvenido al sistema Biblioteca.<br>Prueba Runt.</h2></center>";
	}

}
