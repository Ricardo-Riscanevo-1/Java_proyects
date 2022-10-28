package co.edu.javeriana.citasembajada.modelo;

import java.time.LocalDate;

public class Niño2a12 extends Usuario{

	// ----------------------------
	//  Atributos
	// ----------------------------
	private String escolaridad;
	
	// ----------------------------
	//  Relaciones
	// ----------------------------
	 //----------------------------
	//  Constructores
	// ----------------------------
	
	public Niño2a12(String pNumeroPasaporte,String pNombre, String pPaisNacimiento, String pCiudadNacimiento,LocalDate pFechaNacimiento,String pEmail, String pEscolaridad)
	{
		super(pNumeroPasaporte, pNombre, pPaisNacimiento, pCiudadNacimiento, pFechaNacimiento, pEmail);
		escolaridad = pEscolaridad;
	}
	// ----------------------------
	//  Metodos
	// ----------------------------

	public String getEscolaridad() {
		return escolaridad;
	}

	public void setEscolaridad(String escolaridad) {
		this.escolaridad = escolaridad;
	}

	public double calcularValorVisa(int edad) {
		
		double descuento;
		Visa visa = getSolicitud().getTipoVisa();
		double valor = visa.getValor();
		
		descuento =(double)(18-edad)*0.5;
	
		if( visa instanceof Turismo )
		{
			descuento = descuento+0.2;
		}
		if( visa instanceof Estudiante)
		{
			descuento = descuento+0.1;
		}
		valor = valor*(1-descuento);
		return valor;
	}
	
}
