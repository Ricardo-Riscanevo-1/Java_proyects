package co.edu.javeriana.citasembajada.modelo;
/**
 * @author Ricardo Riscanevo-Juan Sebastian Solano
 **/
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import java.text.DecimalFormat;
import co.edu.javeriana.citasembajada.utils.Utils;

public class SistemaCitas_Embajada implements Serializable {
	/**
	 * 
	 */
	//--------------------
	//	Constantes
	//--------------------

	//--------------------
	//	Atributos de la clase embajada
	//--------------------
	private String id;
	private String paisEmbajada;
	private String moneda;
	private double cambioOficial;
	private float impuesto;

	private Utils util;
	//--------------------
	//	Relaciones
	//--------------------

	/**
	 * Embajada tiene una lista de visas
	 * Embajad tiene una lista de solicitudes
	 * Embajada tiene un HasnMap de usuarios con una llave igual al n�mero de pasaporte de usuario
	 */
	private List<Visa> visas;
	private List<Solicitud> solicitudes;
	private HashMap<String,Usuario> usuarios;


	//--------------------
	//	Constructor
	//--------------------

	public SistemaCitas_Embajada() 
	{
		visas = new ArrayList<Visa>();
		solicitudes = new ArrayList<Solicitud>();
		usuarios = new HashMap<String,Usuario>();
		util = new Utils();
	}

	//-------------------
	// Metodos
	//--------------------
	public String getPaisEmbajada() 
	{
		return paisEmbajada;
	}

	public String getMoneda() 
	{
		return moneda;
	}

	public double getCambioOficial() 
	{
		return cambioOficial;
	}

	public float getImpuesto() 
	{
		return impuesto;
	}

	public String getId() 
	{
		return id;
	}
	public List<Visa> getVisas() 
	{
		return visas;
	}
	public List<Solicitud> getSolicitudes() 
	{
		return solicitudes;
	}
	public HashMap<String,Usuario> getUsuarios() 
	{
		return usuarios;
	}
	public void setPaisEmbajada(String paisEmbajada) 
	{
		this.paisEmbajada = paisEmbajada;
	}
	public void setMoneda(String moneda) 
	{
		this.moneda = moneda;
	}
	public void setCambioOficial(double cambioOficial) 
	{
		this.cambioOficial = cambioOficial;
	}
	public void setImpuesto(float impuesto) 
	{
		this.impuesto = impuesto;
	}
	public void setId(String id) 
	{
		this.id = id;
	}
	public void setVisas(List<Visa> visas) 
	{
		this.visas = visas;
	}
	public void setSolicitudes(List<Solicitud> solicitudes) 
	{
		this.solicitudes = solicitudes;
	}
	public void setUsuarios(HashMap<String,Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	/** 
	 * 
	 *Busca a un usuario en la base de datos a trav�s de un numero de pasaporte, retornando al usuario buscado en caso de encontrarlo
	 * @param String numeroPasaporte;
	 * @return Usuario
	 **/
	//null: No encontro al usuario
	public Usuario buscarUsuario(String numeroPasaporte)
	{
		return usuarios.get(numeroPasaporte);
	}
	/** 
	 * 
	 *Agrega la infomaci�n de un usuario a la lista de usuarios del sistema en el caso de que este usuario no se encuentre en la base de datos, de lo contrario informara que no fue posible agregar al usuario
	 * @param String numeroPasaporte
	 * @param String nombre
	 * @param String paisNacimento
	 * @param String ciudadNacimiento
	 * @param LocalDate fechaNacimiento
	 * @param String email
	 * @return boolean
	 **/
	//true:No existe por lo cual no se agrego
	//false:Existe por lo cual no se agrego
	public boolean agregarUsuario(String numeroPasaporte,String nombre, String paisNacimiento, String ciudadNacimiento,LocalDate fechaNacimiento,String email, String infoAd)
	{
		boolean agregado = false;

		if(buscarUsuario(numeroPasaporte) == null)
		{
			LocalDate ahora = LocalDate.now();
			if(util.obtenerEdad(ahora, fechaNacimiento)<=2)
			{
				Niño0a2 usuario = new Niño0a2(numeroPasaporte, nombre, paisNacimiento, ciudadNacimiento, fechaNacimiento, email , infoAd);
				usuarios.put(numeroPasaporte,usuario);
			
			}
			else if(util.obtenerEdad(ahora, fechaNacimiento)<=12)
			{
				Niño2a12 usuario = new Niño2a12(numeroPasaporte, nombre, paisNacimiento, ciudadNacimiento, fechaNacimiento, email, infoAd);
				usuarios.put(numeroPasaporte,usuario);
				
			}
			else if(util.obtenerEdad(ahora, fechaNacimiento)<=65)
			{
				Adulto usuario = new Adulto(numeroPasaporte, nombre, paisNacimiento, ciudadNacimiento, fechaNacimiento, email);
				usuarios.put(numeroPasaporte,usuario);
			
			}
			else 
			{
				AdultoMayor usuario = new AdultoMayor (numeroPasaporte, nombre, paisNacimiento, ciudadNacimiento, fechaNacimiento, email);
				usuarios.put(numeroPasaporte,usuario);
				
			}

			agregado = true;
		}

		return agregado;
	}

	/** 
	 *Agrega la infomaci�n de una visa a la lista de tipos de visa en el sistema en el caso de que la visa no se encuentre en la base de datos, de lo contrario informara que no se pudo agregar la visa
	 * @param String id
	 * @param String tipo
	 * @param Double tarifa
	 * @param String ciudadNacimiento
	 * @return boolean
	 **/
	//true:No existe por lo cual no se agrego
	//false:Existe por lo cual no se agrego


	/** 
	 * -------------------------------ACTUALMENTE FUERA DE SERVCIO---------------------------
	 *Busca a una visa en la base de datos a trav�s de del nombre del tipo de la visa, retornando la visa buscada en caso de encontrarla
	 * @param String tipoVisa;
	 * @returnVisa
	 **/	
	public Visa buscarVisa(String tipoVisa)
	{ 
		Visa visaTemporal = null;
		if(visas.size()>0)
		{
			for(int i=0;i<visas.size();i++)
			{	visaTemporal = visas.get(i);
			if(tipoVisa.equalsIgnoreCase(visaTemporal.toString())== true)
			{
				return visas.get(i);
			}

			}
		}
		return visaTemporal;
	}

	/** 
	 *  -------------------------------ACTUALMENTE FUERA DE SERVCIO---------------------------
	 *Agrega un requisito a una lista de requisitos que posee una visa existente en la base de datos
	 * @param String tipoVisa
	 * @param String requisito
	 * @return void
	 **/

	public void agregarRequisito(String tipoVisa,String requisito)
	{
		List<Requisito>requisitos = new ArrayList<Requisito>();
		Requisito requisitoTemporal=new Requisito(requisito);//La visa a buscar ya fue creada previamente siendo seguro que va a encontrar la visa

		Visa visa=buscarVisa(tipoVisa);
		requisitos=visa.getRequisitos();	
		requisitos.add(requisitoTemporal);
		visa.setRequisitos(requisitos);
	}

	/** Busca una solicitud en la lista de soicitudes del sistema de acuerdo a un numero de la solicitud dado 
	 * @param int numeroSolicitud 
	 * @return Solicitud
	 * *@throws NullPointerException excepcion acceder una posicion con valor nulo
	 **/
	public Solicitud buscarSolicitud(int numeroSolicitud)
	{
		Solicitud solicitudAux = null;
		for (int i = 0; i < solicitudes.size(); i++) 
		{
			int numeroSolicitudAux = solicitudes.get(i).getCodigo();
			if( numeroSolicitud == numeroSolicitudAux)
			{
				solicitudAux = solicitudes.get(i);
			}
		}
		return solicitudAux;
	}
	/** 
	 *Genera una solicitud asignandole un tipo de visa especifico y una lista de uno o varios usuarios existentes de diferetes edades en la base de datos que no posean una solicitud ya asiganada, y as� posteriomente guardar esta solicitud en el sistema con su respectiva informacion
	 * @param List<String> numerosPasaporte 
	 * @param String visa
	 * @param String ciudadNacimiento
	 * @return Void
	 * @throws NullPointerException
	 **/
	public void solicitarSolicitud(List<String> numerosPasaporte,String nombreVisa, double tarifa ,String idVisa, String[] infoAd)throws RuntimeException
	{	
		List<Usuario> usuarios = new ArrayList<Usuario>();

		for(int i = 0; i <numerosPasaporte.size() ; i++)
		{
			Usuario usuario = buscarUsuario(numerosPasaporte.get(i));
			if(usuario != null && usuario.getSolicitud() == null)//Examina si el usuario ya tiene una solicitud
			{
				usuarios.add(usuario);
			}
		}
		if(usuarios.size()>0) 
		{
			Visa visa = null;
			if(nombreVisa.equalsIgnoreCase("turismo") )
			{
				visa = new Turismo(idVisa, tarifa, Integer.valueOf(infoAd[0]));
			}
			else if(nombreVisa.equalsIgnoreCase("estudiante") )
			{
				visa = new Estudiante(idVisa, tarifa,infoAd[1],infoAd[0]);
			}
			else if( nombreVisa.equalsIgnoreCase("trabajo"))
			{
				visa = new Trabajo(idVisa, tarifa,infoAd[0],infoAd[1]);
			}
			
			Solicitud solicitud = new Solicitud(visa);	
			asignarFechaHora(solicitud);
			asignarSolicitudaUsuario(solicitud, usuarios);
			Solicitud.CONSECUTIVO += 100;
			visa.agregarSolicitud(solicitud);
			visas.add(visa);
			
		}	
		else
		{
			throw new NullPointerException();
		}
	}
	/** 
	 *Permite asignarle a una solicitud ya creada uno o varios usuarios contenidos dentro de una lista, de igual manera que a cada usuario le asigna la solicitud en la cual el se encuentra asociado
	 * @param Solicitud solicitud
	 * @param List<Usuario> usuariosDeSolicitud
	 * @return void
	 **/

	public void asignarSolicitudaUsuario(Solicitud solicitud, List<Usuario> usuariosDeSolicitud)
	{
		Usuario usuarioTemporal;
		solicitud.setUsuariosSolicitantes(usuariosDeSolicitud);
		solicitudes.add(solicitud);
		for(int i=0; i<usuariosDeSolicitud.size();i++)
		{
			usuarioTemporal=usuariosDeSolicitud.get(i);
			usuarioTemporal.setSolicitud(solicitud);
		}
	}
	/** 
	 *Permite asignar a una solicitud ya creada una fecha y hora en dias h�biles en un tiempo de las 8:00 hasta las 16:00 horas, de tal manera que no se repita con la fecha y hora de otras solicitudes.
	 * @param Solicitud solicitud
	 * @return void
	 **/
	public void asignarFechaHora(Solicitud sol)
	{
		LocalDateTime fechaSolicitud;
		LocalDateTime ultimaFechaSolicitud;

		if(solicitudes.size()==0)
		{
			ultimaFechaSolicitud = LocalDateTime.now();
			if(ultimaFechaSolicitud.getDayOfWeek().getValue()>=6 && ultimaFechaSolicitud.getDayOfWeek().getValue()<=7)
			{
				ultimaFechaSolicitud = ultimaFechaSolicitud.plusDays(8-ultimaFechaSolicitud.getDayOfWeek().getValue());	
				ultimaFechaSolicitud = ultimaFechaSolicitud.withHour(0);
				ultimaFechaSolicitud = ultimaFechaSolicitud.withMinute(0);
			}
		}
		else
		{
			ultimaFechaSolicitud = solicitudes.get(solicitudes.size()-1).getFecha();
		}
		
		if(ultimaFechaSolicitud.getHour()>=8 && ultimaFechaSolicitud.getHour()<=14)
		{
			fechaSolicitud = ultimaFechaSolicitud.plusHours(1);	
		}
		else
		{
			if(ultimaFechaSolicitud.getDayOfWeek().getValue()==5 &&ultimaFechaSolicitud.getHour()>14 )
			{
				ultimaFechaSolicitud = ultimaFechaSolicitud.plusDays(3);
			}
				fechaSolicitud = ultimaFechaSolicitud.withHour(8);
		}
		
		sol.setFecha(fechaSolicitud);
	}  
	/** 
	 *Busca aquellas citas que se encuentren programadas para una fecha en especifico, guardando en una lista 
	 *el numero de pasaporte, nombre del usuario, tipo de visa y el c�digo de la solicitud de aquellas solicitudes que concuerden con la fecha 
	 * @param String fecha
	 * @return List<List<String>> 
	 **/
	//Se guarda en la lista: por cada List<String>
	//String.(1) numero de pasaporte  String.(2) nombre del usuario    String tipo de visa(3)    String(4) codigo de la solicitud 

	public List<List<String>> buscarCitasFecha(String fecha)throws NullPointerException 
	{
		List<List<String>> datos = new ArrayList<List<String>>();
		if(solicitudes.size()>0)
		{	
			for(int i = 0; i<solicitudes.size();i++)
			{
				List<Usuario> usuariosDeSolicitud = new ArrayList<Usuario>();	
				usuariosDeSolicitud = solicitudes.get(i).getUsuariosSolicitantes();

				LocalDate fechaSolicitud = solicitudes.get(i).getFecha().toLocalDate();
				System.out.print("fecha"+fechaSolicitud+"   ");
				if(fecha.equals(fechaSolicitud.toString())==true)
				{	
					for(int j = 0; j<usuariosDeSolicitud.size();j++)
					{	
						List<String> datosTemporal=new ArrayList<String>();

						String numeroPasaporte = usuariosDeSolicitud.get(j).getNumeroPasaporte();
						String nombre = usuariosDeSolicitud.get(j).getNombre();
						String tipoVisa = solicitudes.get(i).getTipoVisa().toString();
						String codigo = Integer.toString(solicitudes.get(i).getCodigo());

						datosTemporal.add(numeroPasaporte);
						datosTemporal.add(nombre);
						datosTemporal.add(tipoVisa);
						datosTemporal.add(codigo);

						datos.add(datosTemporal);
					}

				}
			}
		}
		else
		{
			throw new NullPointerException();
		}
		return datos;
	}
	/** 
	 *  -------------------------------ACTUALMENTE FUERA DE SERVCIO---------------------------
	 *Busca cada uno de los requisitos que posee una visa en especifico, de tal manera que pueda guardar cada requisito en una lista con todos lo requisitos
	 * @param String visa
	 * @return List<String> 
	 **/
	public List<String> buscarRequisitosVisa(String visa)throws Exception
	{
		List<String> requisitosVisa = new ArrayList<String>();
		Visa visaEncontrada = buscarVisa(visa);
		if(visaEncontrada!=null)
		{ 
			for(int i=0; i<visaEncontrada.getRequisitos().size(); i++)
			{
				Requisito requisitoTemporal=visaEncontrada.getRequisitos().get(i);
				requisitosVisa.add(requisitoTemporal.getDescripcion());
			}
		}
		else
		{
			throw new Exception("Error en la busqueda de requisitos de la visa");
		}
		return requisitosVisa;
	}
	/** 
	 *Calcula el valor de visa de acuerdo al numero de una solicitud o el numero de pasaporte de un usuario, retornando los datos del usuario y el valor de la visa
	 *o en el caso de ingresar un numero de solicitud, retorna los datos de los usuarios y su respectivo valor de visa
	 * @param String id
	 * @return  List<String[]
	 * @return String[0] numero pasaporte    String[1] nombre     String[2] fecha nacimiento    String[3] valor visa      String[4] valor del impuesto visa      String[5] valor total visa  
	 **/
	
	public List<String[]> calcularVisaXSolicitud (String id){
		
		
		double valor = 0;
		String[] numeroId;
		String[]datosUsuario = new String[6];
		List<String[]> usuarios = new ArrayList<String[]>();
		DecimalFormat df = new DecimalFormat("#.00");
		if(id.startsWith("NP") || id.startsWith("CP"))
		{
		numeroId = id.split("P");
		
		if(id.startsWith("NP"))
		{
			Usuario usuario = buscarUsuario(numeroId[1]);
			if(usuario != null)
			{
				valor = calcularValorVisa(usuario);

				datosUsuario[0] = usuario.getNumeroPasaporte();				
				datosUsuario[1] = usuario.getNombre();
				datosUsuario[2] = String.valueOf(usuario.getFechaNacimiento());
				datosUsuario[3] = String.valueOf(df.format(valor));
				datosUsuario[4] = String.valueOf(df.format(valor*(impuesto/100)));
				datosUsuario[5] = String.valueOf(valor+(valor*(impuesto/100)));

				usuarios.add(datosUsuario);
			}
		}
		if(id.contains("CP"))
		{	
			int numeroSolicitud = Integer.parseInt(numeroId[1]);
			
			Solicitud solicitudEncontrada = buscarSolicitud(numeroSolicitud);
			if( solicitudEncontrada != null)
			{
				for( Usuario usuario : solicitudEncontrada.getUsuariosSolicitantes())
				{
					valor = calcularValorVisa(usuario);
					datosUsuario[0] = usuario.getNumeroPasaporte();				
					datosUsuario[1] = usuario.getNombre();
					datosUsuario[2] = String.valueOf(usuario.getFechaNacimiento());
					datosUsuario[3] = String.valueOf(df.format(valor));
					datosUsuario[4] = String.valueOf(df.format(valor*(impuesto/100)));
					datosUsuario[5] = String.valueOf(df.format(valor+(valor*(impuesto/100))));

					usuarios.add(datosUsuario);
				}
			}
		}
		}
		else
		{
			throw new NullPointerException();
		}
		return usuarios;
		
	}
	/** 
	 *Calcula el valor de visa de un solo usuario de acuerdo a la edad del usuario y el descuento por el tipo de visa que solicit�
	 * @param Usuario usuario
	 * @return double 
	 **/
	public double calcularValorVisa (Usuario usuario){

		double valorVisa = 0;
		LocalDate ahora = LocalDate.now();
		LocalDate fechaNacimiento = usuario.getFechaNacimiento();
		int edad = util.obtenerEdad(ahora,fechaNacimiento);
			
		valorVisa = usuario.calcularValorVisa(edad);

		return valorVisa;
	}
	/** 
	 *Retorna una lista con los datos de cada usuario que recibio un descuento en el valor de su visa
	 * @return List<String[]>
	 * @return String[0] numero Pasaporte		String[1] nombre		String[2] diferencia de Valor
	 **/
	public List<String[]> buscarBeneficiarios()
	 {
		 List<String[]> datosUsuarios = new ArrayList<String[]>();
		 String[] datosUsuario = new String[3];
		 
		 Usuario usuario;
		 for(Map.Entry<String, Usuario> entry : usuarios.entrySet())
		 {	  
			  usuario = entry.getValue();
			 if(usuario.getSolicitud()!= null)
			 {
			 double valorvisa = calcularValorVisa(usuario);
			 Visa visa = usuario.getSolicitud().getTipoVisa();
		
			 if(valorvisa < visa.getValor())
			 {	
				 datosUsuario[0] = usuario.getNumeroPasaporte();
				 datosUsuario[1] = usuario.getNombre();
				 datosUsuario[2] = String.valueOf(visa.getValor() - valorvisa);
				 datosUsuarios.add(datosUsuario);
			 }
			 }
	
		 }
		 return datosUsuarios;
	 }
	public void imprimirSolicitud ()
	 {
		 System.out.println("------Solicitudes--------");
		 for( int i=0; i<solicitudes.size();i++)
		 {
			 System.out.println("codigo "+solicitudes.get(i).getCodigo());
			 System.out.println("estado "+solicitudes.get(i).getEstado());
			 System.out.println("fecha "+solicitudes.get(i).getFecha());
			 System.out.println("Visa "+solicitudes.get(i).getTipoVisa().toString());
			 for(int j = 0; j<solicitudes.get(i).getUsuariosSolicitantes().size();j++)
			 {
				 System.out.println("usuario-----");
				 System.out.println("Num pasaporte "+solicitudes.get(i).getUsuariosSolicitantes().get(j).getNumeroPasaporte());
				 System.out.println("nombre "+solicitudes.get(i).getUsuariosSolicitantes().get(j).getNombre());
				 System.out.println("Pais nacimiento "+solicitudes.get(i).getUsuariosSolicitantes().get(j).getPaisNaciminento());
				 System.out.println("fecha nacimiento "+solicitudes.get(i).getUsuariosSolicitantes().get(j).getFechaNacimiento());
				 
			 }
		 }
		 System.out.println("----------------------");
	 }
	 public void imprimirUsuario()
	 {
		 System.out.println("-----------USUARIOS----------");
		 for( int i=0; i<usuarios.size()*111 ; i+=111)
		 {
			if(usuarios.containsKey(String.valueOf(i)))
			{
				
			 System.out.println(usuarios.get(String.valueOf(i)).getNumeroPasaporte());
			 System.out.println(usuarios.get(String.valueOf(i)).getNombre());
			 System.out.println(usuarios.get(String.valueOf(i)).getPaisNaciminento());
			 System.out.println(usuarios.get(String.valueOf(i)).getFechaNacimiento());
			 if(usuarios.get(String.valueOf(i)) instanceof Niño0a2)
			 {
				 Niño0a2 usuario = (Niño0a2)usuarios.get(String.valueOf(i));
				 System.out.println("Acudiente: "+usuario.getAcudiente());
			 }
			 if(usuarios.get(String.valueOf(i)) instanceof Niño2a12)
			 {
				 Niño2a12 usuario = (Niño2a12)usuarios.get(String.valueOf(i));
				 System.out.println("Escolaridad: "+usuario.getEscolaridad());
			 }
			 System.out.println("----------------------");
			}
		 }
	 }


}
