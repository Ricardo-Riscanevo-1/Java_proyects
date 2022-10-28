package co.edu.javeriana.citasembajada.modelo;

import java.time.LocalDate;

public class AdultoMayor extends Usuario{

	// ----------------------------
	//  Atributos
	// ----------------------------
	// ----------------------------
	//  Relaciones
	// ----------------------------
	//----------------------------
	//  Constructores
	// ----------------------------
	public AdultoMayor(String pNumeroPasaporte,String pNombre, String pPaisNacimiento, String pCiudadNacimiento,LocalDate pFechaNacimiento,String pEmail)
	{
		super(pNumeroPasaporte, pNombre, pPaisNacimiento, pCiudadNacimiento, pFechaNacimiento, pEmail);	
	}
	
	// ----------------------------
	//  Metodos
	// ----------------------------

	public double calcularValorVisa(int edad) {
		
		double descuento = 0.25;
		Visa visa = this.getSolicitud().getTipoVisa();
		double valor = visa.getValor();
		
		if( visa instanceof Turismo)
		{
			descuento = descuento+0.1;
		}
		
		return valor*(1-descuento) ;
	}
}
	
