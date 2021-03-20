package startec.controleportaria.controller;

import javax.websocket.server.PathParam;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import startec.controleportaria.metodosHoraData.PegarDataHora;
import startec.controleportaria.model.CadastroPessoa;
import startec.controleportaria.model.ControleHorario;
import startec.controleportaria.repository.ControleHorarioRepository;
import startec.controleportaria.repository.PessoaRepository;

@Controller
public class ControleHorarioController {
	
	@Autowired
	private ControleHorarioRepository controleHorarioRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/controlehorario")
	public ModelAndView inicio() {
		ModelAndView modelAndView = new ModelAndView("controle/controlehorario");
		modelAndView.addObject("pessoaobj", new CadastroPessoa());
		return modelAndView;
	}
	
	
	//metodo para salvar as informacoes no BD
	@RequestMapping(method = RequestMethod.POST, value = "**/salvarControleHorario")
	public ModelAndView salvar(ControleHorario controleHorario) {
		
		
		controleHorarioRepository.save(controleHorario);
		ModelAndView modelAndView = new ModelAndView("controle/controlehorario");
		Iterable<ControleHorario> pessoaIt = controleHorarioRepository.findAll(); //buscar os dados no banco de dados
		modelAndView.addObject("buscarControleHorario", pessoaIt);
		modelAndView.addObject("pessoaobj", new CadastroPessoa());
		return modelAndView;
	}
	
	
	//Metodo para buscar dados
	@RequestMapping(method = RequestMethod.GET, value = "/listaControleHorario")
	public ModelAndView consultarControleHorario() {
		
		
		
		ModelAndView modelAndView = new ModelAndView("controle/controlehorario");
		Iterable<ControleHorario> controleHorarioIt = controleHorarioRepository.findAll();
		modelAndView.addObject("buscarControleHorario", controleHorarioIt);
		modelAndView.addObject("pessoaobj", new CadastroPessoa());
		
		return modelAndView;
	}
	
	//Metodo para levar o usuario para a tela de controle de horario // usa o metodo editar
	@GetMapping("/passarDadosControle/{idpessoa}")
	public ModelAndView editar(@PathVariable("idpessoa") Long idpessoa) {
		
		Optional<CadastroPessoa> passarDadosControle = pessoaRepository.findById(idpessoa);
		
		ModelAndView modelAndView = new ModelAndView("controle/controlehorario");
		modelAndView.addObject("pessoaobj", passarDadosControle.get()); //pessoaobj = carrega os dados na tela e preenche os campos quando aperta em editar
		return modelAndView;
	}
	

}
