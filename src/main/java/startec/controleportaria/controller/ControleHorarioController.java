package startec.controleportaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import startec.controleportaria.repository.ControleHorarioRepository;

@Controller
public class ControleHorarioController {
	
	@Autowired
	private ControleHorarioRepository controleHorarioRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/controlehorario")
	public String inicio() {
		
		return "controle/controlehorario";
		
	}

}
