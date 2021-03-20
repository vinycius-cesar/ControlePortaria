package startec.controleportaria.model;

import java.io.Serializable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import startec.controleportaria.metodosHoraData.PegarDataHora;

@Entity
public class ControleHorario implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	private String dataAtual;
	private String horaEntrada;
	private String horaSaida;
	private String empresaVisitante;
	private String cpfVisitante;
	

	private String nomeVisitante;
	private String finalidade;
	private String falarComColaborador;
	private String tipoVeiculo;
	private String placaVeiculo;
	
	
	
	public String getNomeVisitante() {
		return nomeVisitante;
	}
	public void setNomeVisitante(String nomeVisitante) {
		this.nomeVisitante = nomeVisitante;
	}
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
	public String getEmpresaVisitante() {
		return empresaVisitante;
	}
	public void setEmpresaVisitante(String empresaVisitante) {
		this.empresaVisitante = empresaVisitante;
	}
	public String getCpfVisitante() {
		return cpfVisitante;
	}
	public void setCpfVisitante(String cpfVisitante) {
		this.cpfVisitante = cpfVisitante;
	}
	public String getFinalidade() {
		return finalidade;
	}
	public void setFinalidade(String finalidade) {
		this.finalidade = finalidade;
	}
	public String getFalarComColaborador() {
		return falarComColaborador;
	}
	public void setFalarComColaborador(String falarComColaborador) {
		this.falarComColaborador = falarComColaborador;
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
	
	


}
