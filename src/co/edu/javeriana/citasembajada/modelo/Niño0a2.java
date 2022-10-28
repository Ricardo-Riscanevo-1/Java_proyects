package co.edu.javeriana.citasembajada.modelo;

import java.time.LocalDate;

public class Niño0a2 extends Usuario{

	// ----------------------------
	//  Atributos
	// ----------------------------
	private String acudiente;

	// ----------------------------
	//  Relaciones
	// ----------------------------
	//----------------------------
	//  Constructores
	// ----------------------------
	public Niño0a2(String pNumeroPasaporte,String pNombre, String pPaisNacimiento, String pCiudadNacimiento,LocalDate pFechaNacimiento,String pEmail, String pAcudiente) {
		super(pNumeroPasaporte, pNombre, pPaisNacimiento, pCiudadNacimiento, pFechaNacimiento, pEmail);
		acudiente = pAcudiente;
	}
	
	// ----------------------------
	//  Metodos
	// ----------------------------
	public String getAcudiente() {
		return acudiente;
	}

	public void setAcudiente(String acudiente) {
		this.acudiente = acudiente;
	}

	public double calcularValorVisa(int edad) {
		Visa visa = getSolicitud().getTipoVisa();
		double valor = visa.getValor();
		valor = valor*(0.9);
		
		return valor;
	}
}