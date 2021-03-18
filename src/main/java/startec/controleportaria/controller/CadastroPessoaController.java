package startec.controleportaria.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import startec.controleportaria.model.CadastroPessoa;
import startec.controleportaria.repository.PessoaRepository;

@Controller
public class CadastroPessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/cadastropessoa")
	public String inicio() {
		return "cadastro/cadastropessoa";
	}

	//Metodo de cadastro no banco de dados
	@RequestMapping(method = RequestMethod.POST, value = "/salvarpessoa")
	public ModelAndView salvar(CadastroPessoa pessoa) {
		pessoaRepository.save(pessoa);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		Iterable<CadastroPessoa> pessoasIt = pessoaRepository.findAll(); //Busca os dados do banco de dados
		modelAndView.addObject("buscarPessoasTable", pessoasIt); 
		
		return modelAndView; //tela que fica dps de salvar a pessoa
	}
	
	//metodo para busar os dados
	@RequestMapping(method = RequestMethod.GET, value="/listapessoas")
	public ModelAndView buscarPessoas() {
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		Iterable<CadastroPessoa> pessoasIt = pessoaRepository.findAll(); //Busca os dados do banco de dados
		modelAndView.addObject("buscarPessoasTable", pessoasIt); //linka com os dados da tabela e puxa todos os dados do banco
		return modelAndView;
	}
	
	//metodo para editar
	@GetMapping("/editarpessoa/{idpessoa}")
	public ModelAndView editar(@PathVariable("idpessoa") Long idpessoa) {
		
		Optional<CadastroPessoa> editarPessoa = pessoaRepository.findById(idpessoa);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		modelAndView.addObject("pessoaobj", editarPessoa.get()); //pessoaobj = carrega os dados na tela e preenche os campos quando aperta em editar
		return modelAndView;
	}

}
