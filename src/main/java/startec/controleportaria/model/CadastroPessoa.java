package startec.controleportaria.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class CadastroPessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	private String nomeVisitante;
	private int cpfVisitante;
	private String empresaVisitante;
	
	
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
	public int getCpfVisitante() {
		return cpfVisitante;
	}
	public void setCpfVisitante(int cpfVisitante) {
		this.cpfVisitante = cpfVisitante;
	}
	public String getEmpresaVisitante() {
		return empresaVisitante;
	}
	public void setEmpresaVisitante(String empresaVisitante) {
		this.empresaVisitante = empresaVisitante;
	}
	
	
	
	

}
