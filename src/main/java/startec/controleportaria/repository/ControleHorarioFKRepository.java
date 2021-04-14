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
			  value = "select * from controle_horariofk where cadastro_pessoa_id = ?1" , 
			  nativeQuery = true)
	public List<ControleHorarioFK> getControleHorario(Long pessoaid); 
	
	
	@Query(
			  value = "select * from controle_horariofk where cadastro_pessoa_id = ?1 and saidaok is null", 
			  nativeQuery = true)
	public List<ControleHorarioFK> getMarcarSaida(Long pessoaid); 
	
	
	
	@Query(
			  value = "select * from controle_horariofk where id = ?1", 
			  nativeQuery = true)
	public List<ControleHorarioFK> getControleHorarioFKs(Long idcontrolefk);
	
	@Query(
			  value = "select cadastro_pessoa.nome_visitante, controle_horariofk.data_atual, controle_horariofk.hora_entrada\r\n" + 
			  		"	from cadastro_pessoa, controle_horariofk\r\n" + 
			  		"	where cadastro_pessoa.id = ? and controle_horariofk.cadastro_pessoa_id = ? and saidaok is null", 
			  nativeQuery = true)
	public List<ControleHorarioFK> getPessoaSaida(Long idpessoa, Long idpessoafk);
	
}
