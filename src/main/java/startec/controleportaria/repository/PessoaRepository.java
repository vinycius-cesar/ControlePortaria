package startec.controleportaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import startec.controleportaria.model.CadastroEmpresa;
import startec.controleportaria.model.CadastroPessoa;
import startec.controleportaria.model.ControleHorarioFK;

@Repository
@Transactional
public interface PessoaRepository extends CrudRepository<CadastroPessoa, Long> {
	
	
}
