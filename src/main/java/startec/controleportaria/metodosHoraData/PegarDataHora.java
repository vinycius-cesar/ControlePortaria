package startec.controleportaria.metodosHoraData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class PegarDataHora {
	
static Date dataHoraAtual = new Date();
	
	
	
	public String retornarData() {
		String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
		return data;
		
	}
	
	public String retornarHora() {
		String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
		return hora;
		
	}
	

}
