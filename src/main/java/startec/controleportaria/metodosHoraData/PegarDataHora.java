package startec.controleportaria.metodosHoraData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class PegarDataHora {
	
	public String pegarData() {
		
		final DateFormat df = new SimpleDateFormat("ddMMyyyy");
		final Calendar cal = Calendar.getInstance();
		String dataAtual = (df.format(cal.getTime()));
		
		return dataAtual;
		
		
		
	}
	
	

}
