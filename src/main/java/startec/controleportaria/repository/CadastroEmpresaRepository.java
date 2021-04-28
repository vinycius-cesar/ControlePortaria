package startec.controleportaria.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import startec.controleportaria.model.CadastroEmpresa;

@Repository
@Transactional
public interface CadastroEmpresaRepository extends CrudRepository<CadastroEmpresa, Long> {
	@Query(
			  value = "select empresa from cadastro_empresa", 
			  nativeQuery = true)
	public List<CadastroEmpresa> getSelectEmpresa();


}
