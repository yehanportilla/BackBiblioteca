package co.com.runt.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.runt.entities.Tipodocumentos;
import co.com.runt.repository.TipodocumentoRepository;

@RestController
@RequestMapping("/api")
public class TipodocServicios {

	@Autowired
	TipodocumentoRepository tipodocumentoRepository;
	@GetMapping("listipodoc")
	public List<Tipodocumentos> listarDocumentos(){
		return tipodocumentoRepository.findAll();
	}
	
	
}
