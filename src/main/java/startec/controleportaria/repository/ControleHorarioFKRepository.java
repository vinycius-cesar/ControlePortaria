package startec.controleportaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import startec.controleportaria.model.ControleHorarioFK;



@Repository
@Transactional
public interface ControleHorarioFKRepository extends CrudRepository<ControleHorarioFK, Long> {

	@Query(
			  value = "select * from controle_horariofk where cadastro_pessoa_id = ?1", 
			  nativeQuery = true)
	public List<ControleHorarioFK> getControleHorario(Long pessoaid); 
	
}
