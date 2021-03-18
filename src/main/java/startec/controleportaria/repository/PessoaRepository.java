package startec.controleportaria.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import startec.controleportaria.model.CadastroPessoa;

@Repository
@Transactional
public interface PessoaRepository extends CrudRepository<CadastroPessoa, Long> {

}
