package co.edu.javeriana.citasembajada.modelo;

import java.time.LocalDate;

public class Adulto extends Usuario{

	// ----------------------------
	//  Atributos
	// ----------------------------
	// ----------------------------
	//  Relaciones
	// ---------------------------
	//----------------------------
	//  Constructores
	// ----------------------------
	public Adulto(String pNumeroPasaporte,String pNombre, String pPaisNacimiento, String pCiudadNacimiento,LocalDate pFechaNacimiento,String pEmail)
	{
		super(pNumeroPasaporte, pNombre, pPaisNacimiento, pCiudadNacimiento, pFechaNacimiento, pEmail);	
	}
	
	// ----------------------------
	//  Metodos
	// ----------------------------
	public double calcularValorVisa(int edad)
	{ 
		Visa visa = getSolicitud().getTipoVisa();
		return visa.getValor();	
	}
}
