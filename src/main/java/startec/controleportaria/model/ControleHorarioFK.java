package startec.controleportaria.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;

@Entity
public class ControleHorarioFK {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String dataAtual;
	private String horaEntrada;
	private String horaSaida;
	private String finalidade;
	private String FalarComColaborador;
	private String tipoVeiculo;
	private String placaVeiculo;
	private String entradaOK;
	private String saidaOK;
	
	@ForeignKey(name = "pessoa_id")
	@ManyToOne
	private CadastroPessoa cadastroPessoa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(String dataAtual) {
		this.dataAtual = dataAtual;
	}

	public String getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public String getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(String horaSaida) {
		this.horaSaida = horaSaida;
	}

	public String getFinalidade() {
		return finalidade;
	}

	public void setFinalidade(String finalidade) {
		this.finalidade = finalidade;
	}

	public String getFalarComColaborador() {
		return FalarComColaborador;
	}

	public void setFalarComColaborador(String falarComColaborador) {
		FalarComColaborador = falarComColaborador;
	}

	public String getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(String tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	public String getPlacaVeiculo() {
		return placaVeiculo;
	}

	public void setPlacaVeiculo(String placaVeiculo) {
		this.placaVeiculo = placaVeiculo;
	}

	public String getEntradaOK() {
		return entradaOK;
	}

	public void setEntradaOK(String entradaOK) {
		this.entradaOK = entradaOK;
	}

	public String getSaidaOK() {
		return saidaOK;
	}

	public void setSaidaOK(String saidaOK) {
		this.saidaOK = saidaOK;
	}

	public CadastroPessoa getCadastroPessoa() {
		return cadastroPessoa;
	}

	public void setCadastroPessoa(CadastroPessoa cadastroPessoa) {
		this.cadastroPessoa = cadastroPessoa;
	}

	
	

}
