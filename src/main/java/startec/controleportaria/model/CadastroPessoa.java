package startec.controleportaria.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class CadastroPessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	private String nomeVisitante;
	private Long cpfVisitante;
	private String empresaVisitante;
	
	@OneToMany(mappedBy = "cadastroPessoa")
	private List<ControleHorarioFK> controleHorario;
	
	
	public List<ControleHorarioFK> getControleHorario() {
		return controleHorario;
	}
	public void setControleHorario(List<ControleHorarioFK> controleHorario) {
		this.controleHorario = controleHorario;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getNomeVisitante() {
		return nomeVisitante;
	}
	public void setNomeVisitante(String nomeVisitante) {
		this.nomeVisitante = nomeVisitante;
	}
	public Long getCpfVisitante() {
		return cpfVisitante;
	}
	public void setCpfVisitante(Long cpfVisitante) {
		this.cpfVisitante = cpfVisitante;
	}
	public String getEmpresaVisitante() {
		return empresaVisitante;
	}
	public void setEmpresaVisitante(String empresaVisitante) {
		this.empresaVisitante = empresaVisitante;
	}
	
	
	
	

}
