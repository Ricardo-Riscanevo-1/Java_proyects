package co.edu.javeriana.citasembajada.modelo;
/**
 * @author Ricardo Riscanevo-Juan Sebastian Solano
 **/
import java.io.Serializable;
import java.time.LocalDate;


public abstract class Usuario implements Serializable  {

	// ----------------------------
	//  Constantes
	// ----------------------------


	// ----------------------------
	//  Atributos
	// ----------------------------
	/**
	 * 
	 */
	protected String nombre;
	protected String numeroPasaporte;
	protected String email;
	protected LocalDate fechaNacimiento;
	protected String paisNaciminento;
	protected String ciudadNacimiento;
	
	// ----------------------------

	//  Relaciones
	// ----------------------------
	/**
	 * Un usuario tiene una única solititud
	 */
	protected Solicitud solicitud;

	// ----------------------------
	//  Constructores
	// ----------------------------
	public Usuario(String pNumeroPasaporte,String pNombre, String pPaisNacimiento, String pCiudadNacimiento,LocalDate pFechaNacimiento,String pEmail)
	{
		numeroPasaporte = pNumeroPasaporte;
		nombre = pNombre;
		fechaNacimiento = pFechaNacimiento;
		paisNaciminento = pPaisNacimiento;
		ciudadNacimiento = pCiudadNacimiento;
		email = pEmail;
	}
	
	// ----------------------------
	//  Metodos
	// ----------------------------
	
	public String getNombre() {
		return nombre;
	}

	public Solicitud getSolicitud() {
		return solicitud;
	}
	
	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}
	
	public String getNumeroPasaporte() {
		return numeroPasaporte;
	}
	
	public String getEmail() {
		return email;
	}
	
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public String getPaisNaciminento() {
		return paisNaciminento;
	}
	
	public String getCiudadNacimiento() {
		return ciudadNacimiento;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setNumeroPasaporte(String numeroPasaporte) {
		this.numeroPasaporte = numeroPasaporte;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setPaisNaciminento(String paisNaciminento) {
		this.paisNaciminento = paisNaciminento;
	}

	public void setCiudadNacimiento(String ciudadNacimiento) {
		this.ciudadNacimiento = ciudadNacimiento;
	}
	
	public abstract double calcularValorVisa(int edad);
	
	
}