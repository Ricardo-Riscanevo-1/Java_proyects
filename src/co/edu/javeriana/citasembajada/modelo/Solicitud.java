package co.edu.javeriana.citasembajada.modelo;
/**
 * @author Ricardo Riscanevo-Juan Sebastian Solano
 **/
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Solicitud implements Serializable{
	// ----------------------------
	//  Constantes
	// ----------------------------
	
	public static int CONSECUTIVO=100;

	// ----------------------------
	//  Atributos
	// ----------------------------
	/**
	 * 
	 */
	private int codigo;
	private String estado;
	private LocalDateTime fecha;

	// ----------------------------
	//  Relaciones
	// ----------------------------
	/**
	 * Una solicitud tiene una lista de usuarios pertenecientes a sola esa solicitud
	 * Una solicitud tiene un tipo de visa
	 */
	private List<Usuario> usuariosSolicitantes;
	private Visa tipoVisa;

	// ----------------------------
	//	Cosnstructor
	// ----------------------------
	public Solicitud( Visa pVisa) 
	{
		codigo = CONSECUTIVO;
		estado = "Pendiente";
		tipoVisa = pVisa;
		usuariosSolicitantes = new ArrayList<Usuario>();	
		
	}
	
	//-----------------------------
	// Metodos
	//-----------------------------
	

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public List<Usuario> getUsuariosSolicitantes() {
		return usuariosSolicitantes;
	}

	public void setUsuariosSolicitantes(List<Usuario> usuariosSolicitantes) {
		this.usuariosSolicitantes = usuariosSolicitantes;
	}

	public Visa getTipoVisa() {
		return tipoVisa;
	}

	public void setTipoVisa(Visa tipoVisa) {
		this.tipoVisa = tipoVisa;
	}

	public Usuario agregarUsuarioSolicitud(Usuario pUsuario)
	{
		usuariosSolicitantes.add(pUsuario);
		return pUsuario;
	}
	


}
