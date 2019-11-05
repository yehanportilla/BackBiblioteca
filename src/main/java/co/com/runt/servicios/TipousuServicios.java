package co.com.runt.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.runt.entities.Tipousuarios;
import co.com.runt.repository.TipousuarioRepository;

@RestController
@RequestMapping("/api")
public class TipousuServicios {

	@Autowired
	TipousuarioRepository tipousuarioRepository;

	@GetMapping("listipousu")
	public List<Tipousuarios> listarTipousu() {

		return tipousuarioRepository.findAll();
	}

}
