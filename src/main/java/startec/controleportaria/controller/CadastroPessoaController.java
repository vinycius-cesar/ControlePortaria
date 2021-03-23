package startec.controleportaria.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import startec.controleportaria.model.CadastroPessoa;
import startec.controleportaria.model.ControleHorario;
import startec.controleportaria.model.ControleHorarioFK;
import startec.controleportaria.repository.ControleHorarioFKRepository;
import startec.controleportaria.repository.ControleHorarioRepository;
import startec.controleportaria.repository.PessoaRepository;

@Controller
public class CadastroPessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ControleHorarioFKRepository controleHorarioFKRepository;
	

	@RequestMapping(method = RequestMethod.GET, value = "/cadastropessoa")
	public ModelAndView inicio() {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		modelAndView.addObject("pessoaobj", new CadastroPessoa());
		Iterable<CadastroPessoa> pessoasIt = pessoaRepository.findAll(); //Busca os dados do banco de dados
		modelAndView.addObject("buscarPessoasTable", pessoasIt);
		
		return modelAndView;
	}

	//Metodo de cadastro no banco de dados
	@RequestMapping(method = RequestMethod.POST, value = "**/salvarpessoa")
	public ModelAndView salvar(CadastroPessoa pessoa) {
		pessoaRepository.save(pessoa);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		Iterable<CadastroPessoa> pessoasIt = pessoaRepository.findAll(); //Busca os dados do banco de dados
		
		modelAndView.addObject("buscarPessoasTable", pessoasIt); 
		modelAndView.addObject("pessoaobj", new CadastroPessoa());
		
		return modelAndView; //tela que fica dps de salvar a pessoa
	}
	
	//metodo para busar os dados
	@RequestMapping(method = RequestMethod.GET, value="/listapessoas")
	public ModelAndView buscarPessoas() {
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		Iterable<CadastroPessoa> pessoasIt = pessoaRepository.findAll(); //Busca os dados do banco de dados
		modelAndView.addObject("buscarPessoasTable", pessoasIt); //linka com os dados da tabela e puxa todos os dados do banco
		modelAndView.addObject("pessoaobj", new CadastroPessoa());
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
	
	
	//metodo para pegar o usuario e marcar entrada e saida // estou copiando o metodo de telefone
	@GetMapping("/controlehorariofk/{idpessoa}")
	public ModelAndView controleHorario(@PathVariable("idpessoa") Long idpessoa) {
		
		Optional<CadastroPessoa> editarPessoa = pessoaRepository.findById(idpessoa);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/controlehorariofk");
		modelAndView.addObject("pessoaobj", editarPessoa.get()); //pessoaobj = carrega os dados na tela e preenche os campos quando aperta em editar
		modelAndView.addObject("tblControleEntrada", controleHorarioFKRepository.getControleHorario(idpessoa));
		modelAndView.addObject("tblControleSaida", controleHorarioFKRepository.getMarcarSaida(idpessoa));
		modelAndView.addObject("controleobj", new ControleHorarioFK()); 
		return modelAndView;
	}
	
	@PostMapping("**/registrarEntrada/{pessoaid}")
	public ModelAndView registrarEntrada( ControleHorarioFK controleHorariofk, @PathVariable("pessoaid") Long pessoaid) {
		CadastroPessoa cadastroPessoa = pessoaRepository.findById(pessoaid).get();
		controleHorariofk.setCadastroPessoa(cadastroPessoa);
		controleHorarioFKRepository.save(controleHorariofk);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/controlehorariofk");
		modelAndView.addObject("pessoaobj", cadastroPessoa);
		modelAndView.addObject("tblControleEntrada", controleHorarioFKRepository.getControleHorario(pessoaid));
		modelAndView.addObject("tblControleSaida", controleHorarioFKRepository.getMarcarSaida(pessoaid));
		modelAndView.addObject("controleobj", new ControleHorarioFK()); 
		return modelAndView;
	}
	
	
	
	
	@GetMapping("/salvarsaida/{idcontrolefk}")
	public ModelAndView editarSalvarSaida(@PathVariable("idcontrolefk") Long idcontrolefk) { //utilizando o metodo editar para salvar a saida da pessoa

       // Optional<CadastroPessoa> editarPessoa = pessoaRepository.findById(idpessoa);
        Optional<ControleHorarioFK> editarSaida = controleHorarioFKRepository.findById(idcontrolefk);
		CadastroPessoa cadastroPessoa = controleHorarioFKRepository.findById(idcontrolefk).get().getCadastroPessoa();

		ModelAndView modelAndView = new ModelAndView("cadastro/controlehorariofk");
		modelAndView.addObject("controleobj", editarSaida.get()); //pessoaobj = carrega os dados na tela e preenche os campos quando aperta em editar
		modelAndView.addObject("pessoaobj", cadastroPessoa);
		modelAndView.addObject("tblControleEntrada", controleHorarioFKRepository.getControleHorario(cadastroPessoa.getId()));
		modelAndView.addObject("tblControleSaida", controleHorarioFKRepository.getMarcarSaida(cadastroPessoa.getId()));
		return modelAndView;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	@RequestMapping(method = RequestMethod.GET, value = "/salvarsaida")
	public ModelAndView pagesaida() {
		ModelAndView modelAndView = new ModelAndView("cadastro/saida");
	//	modelAndView.addObject("pessoaobj", new CadastroPessoa());
	//	Iterable<CadastroPessoa> pessoasIt = pessoaRepository.findAll(); //Busca os dados do banco de dados
	//	modelAndView.addObject("buscarPessoasTable", pessoasIt);
		
		return modelAndView;
	}
	*/
	
	
	//metodo para editar
		
	
	
	
	
	
	
	/*
	@GetMapping("/removerteste/{idcontrolefk}")
	public ModelAndView excluir(@PathVariable("idcontrolefk") Long idcontrolefk) {
 
		CadastroPessoa controle = controleHorarioFKRepository.findById(idcontrolefk).get().getCadastroPessoa();
		controleHorarioFKRepository.deleteById(idcontrolefk);

		ModelAndView modelAndView = new ModelAndView("cadastro/controlehorariofk");
		modelAndView.addObject("pessoaobj", controle);
		modelAndView.addObject("tblControleEntrada", controleHorarioFKRepository.getControleHorario(controle.getId()));
		return modelAndView;
	}
	
	*/
	
	//metodo para excluir
	/*
			@GetMapping("/removerpessoa/{idpessoa}")
			public ModelAndView excluir(@PathVariable("idpessoa") Long idpessoa) {

				pessoaRepository.deleteById(idpessoa);

				ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
				modelAndView.addObject("buscarPessoasTable", pessoaRepository.findAll()); //pessoaobj = carrega os dados na tela e preenche os campos quando aperta em editar
				modelAndView.addObject("pessoaobj", new CadastroPessoa());
				return modelAndView;
			}
*/
}