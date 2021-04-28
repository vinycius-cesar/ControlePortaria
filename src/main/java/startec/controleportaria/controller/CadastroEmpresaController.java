package startec.controleportaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import startec.controleportaria.model.CadastroEmpresa;
import startec.controleportaria.model.CadastroPessoa;
import startec.controleportaria.repository.CadastroEmpresaRepository;
import startec.controleportaria.repository.ControleHorarioFKRepository;
import startec.controleportaria.repository.PessoaRepository;

@Controller
public class CadastroEmpresaController {
	
	@Autowired
	private CadastroEmpresaRepository cadastroEmpresaRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ControleHorarioFKRepository controleHorarioFKRepository;
	
	
	@RequestMapping(method = RequestMethod.POST, value = "**/salvarempresa")
	public ModelAndView salvarEmpresa(CadastroEmpresa empresa) {
		cadastroEmpresaRepository.save(empresa);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		Iterable<CadastroPessoa> pessoasIt = pessoaRepository.findAll(); // Busca os dados do banco de dados
		Iterable<CadastroEmpresa> empresaIt = cadastroEmpresaRepository.findAll();
		modelAndView.addObject("selectEmpresa", empresaIt);
		modelAndView.addObject("buscarPessoasTable", pessoasIt);
		modelAndView.addObject("pessoaobj", new CadastroPessoa());
		
		return modelAndView;
	}
	
	
	//LISTAR NO SELECT - FORM EMPRESA
	@RequestMapping(method = RequestMethod.GET, value = ("/cadastropessoa"))
	public ModelAndView selectEmpresa() {		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		Iterable<CadastroPessoa> pessoasIt = pessoaRepository.findAll(); // Busca os dados do banco de dados
		Iterable<CadastroEmpresa> empresaIt = cadastroEmpresaRepository.findAll();
		modelAndView.addObject("selectEmpresa", empresaIt);
		
		modelAndView.addObject("pessoaobj", new CadastroPessoa());
		modelAndView.addObject("buscarPessoasTable", pessoasIt);
		
    return modelAndView;
	}
	
}
