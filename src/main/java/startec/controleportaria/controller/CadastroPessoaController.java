package startec.controleportaria.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

<<<<<<< HEAD
import startec.controleportaria.model.CadastroEmpresa;
=======
import startec.controleportaria.metodosHoraData.PegarDataHora;
>>>>>>> d83238da3dad65842ca52d49a9be62673a03f193
import startec.controleportaria.model.CadastroPessoa;

import startec.controleportaria.model.ControleHorarioFK;
import startec.controleportaria.repository.CadastroEmpresaRepository;
import startec.controleportaria.repository.ControleHorarioFKRepository;

import startec.controleportaria.repository.PessoaRepository;

@Controller
public class CadastroPessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ControleHorarioFKRepository controleHorarioFKRepository;
	
	@Autowired
	private CadastroEmpresaRepository cadastroEmpresaRepository;
	
	

	//Metodo de cadastro no banco de dados
	@RequestMapping(method = RequestMethod.POST, value = "**/salvarpessoa", consumes = {"multipart/form-data"})
	public ModelAndView salvar(CadastroPessoa pessoa, final MultipartFile file) throws IOException {
		
		if(file.getSize() > 0) {
			pessoa.setFileVisitante(file.getBytes());
			pessoa.setTipoFileVisitante(file.getContentType());
			pessoa.setNomefileVisitante(file.getOriginalFilename());
		}else {
			if(pessoa.getId() != null && pessoa.getId() > 0) {//editando
				CadastroPessoa pessoaTemp = pessoaRepository.findById(pessoa.getId()).get();
				pessoa.setFileVisitante(pessoaTemp.getFileVisitante());
				pessoa.setTipoFileVisitante(pessoaTemp.getTipoFileVisitante());
				pessoa.setNomefileVisitante(pessoaTemp.getNomefileVisitante());
			}
		}
		pessoaRepository.save(pessoa);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		Iterable<CadastroPessoa> pessoasIt = pessoaRepository.findAll(); //Busca os dados do banco de dados
		Iterable<CadastroEmpresa> empresaIt = cadastroEmpresaRepository.findAll();
		modelAndView.addObject("selectEmpresa", empresaIt);
		modelAndView.addObject("buscarPessoasTable", pessoasIt); 
		modelAndView.addObject("pessoaobj", new CadastroPessoa());
		
		return modelAndView; //tela que fica dps de salvar a pessoa
	}
	
	//metodo para busar os dados
	@RequestMapping(method = RequestMethod.GET, value="/listapessoas")
	public ModelAndView buscarPessoas() {
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		
		Iterable<CadastroPessoa> pessoasIt = pessoaRepository.findAll(); //Busca os dados do banco de dados
		Iterable<CadastroEmpresa> empresaIt = cadastroEmpresaRepository.findAll();
		modelAndView.addObject("selectEmpresa", empresaIt);
		modelAndView.addObject("buscarPessoasTable", pessoasIt); //linka com os dados da tabela e puxa todos os dados do banco
		modelAndView.addObject("pessoaobj", new CadastroPessoa());
		return modelAndView;
	}
	
	//metodo para editar
	@GetMapping("/editarpessoa/{idpessoa}")
	public ModelAndView editar(@PathVariable("idpessoa") Long idpessoa) {
		
		Optional<CadastroPessoa> editarPessoa = pessoaRepository.findById(idpessoa);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		Iterable<CadastroEmpresa> empresaIt = cadastroEmpresaRepository.findAll();
		modelAndView.addObject("selectEmpresa", empresaIt);
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
		controleHorariofk.setDataAtual(new PegarDataHora().retornarData());
		controleHorariofk.setHoraEntrada(new PegarDataHora().retornarHora());
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
	
	
	//MÉTODO PARA BAIXAR O ARQUIVO 
	@GetMapping("**/baixarfile/{idpessoa}")
	public void baixarfile(@PathVariable("idpessoa") Long idpessoa, HttpServletResponse response) throws IOException {
		/*CONSULTAR O OBJETO PESSOA NO BANCO DE DADOS*/
		CadastroPessoa pessoa = pessoaRepository.findById(idpessoa).get();
		if(pessoa.getFileVisitante() != null) {
			/*SETAR O TAMANHO DA RESPOSTA*/
			response.setContentLength(pessoa.getFileVisitante().length);
			/*TIPO DO ARQUIVO PARA O DOWNLOAD*/
			response.setContentType(pessoa.getTipoFileVisitante());
			/*DEFINE O CABEÇALHO DA RESPOSTA*/
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", pessoa.getNomefileVisitante());
			response.setHeader(headerKey, headerValue);
			/*FINALIZA A RESPOSTA*/
			response.getOutputStream().write(pessoa.getFileVisitante());
		}
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