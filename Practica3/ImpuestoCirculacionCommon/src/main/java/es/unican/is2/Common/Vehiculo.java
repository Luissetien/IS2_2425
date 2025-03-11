package es.unican.is2.Common;
import java.time.LocalDate;
import java.time.Year;

/**
 * Clase abstracta que representa un vehiculo. 
 * Cada vehiculo tiene una matricula unica.
 */
public abstract class Vehiculo {

	// Clave primaria autogenerada
	private long id;

	private String matricula;
	private LocalDate fechaMatriculacion;
	private TipoMotor motor;

	// TODO
	public Vehiculo(long id, String matricula, LocalDate fechaMatriculacion, TipoMotor motor) {
		
		this.id = id;
		
		this.matricula = matricula;
		
		this.fechaMatriculacion = fechaMatriculacion;
		
		this.motor = motor;
	}

	/**
	 * Retorna la matricula del vehiculo.
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * Retorna la fecha de primera matriculacion del vehiculo.
	 */
	public LocalDate getFechaMatriculacion() {
		return fechaMatriculacion;
	}

	/**
	 * Retorna el tipo de motor del vehiculo.
	 */
	public TipoMotor getMotor() {
		return motor;
	}

	/**
	 * Retorna el identificador del vehiculo.
	 */
	public long getId() {
		return id;
	}

	public abstract double precioImpuesto();

	public double calcularDescuento() {

	    double descuentoAplicado = 0;

	    int anhoAct = Year.now().getValue();
	    
	    int antiguedad = anhoAct - fechaMatriculacion.getYear();

	    if (antiguedad > 20) {
	    	
	        descuentoAplicado = 1;
	    }
	    
	    else if (getMotor() == TipoMotor.ELECTRICO) {
	    	
	        descuentoAplicado = 0.8;
	    }
	    
	    else if (getMotor() == TipoMotor.HIBRIDO && antiguedad <= 5) {
	    	
	        descuentoAplicado = 0.7;
	    }
	    
	    else if (getMotor() == TipoMotor.GAS && antiguedad <= 2) {
	    	
	        descuentoAplicado = 0.6;
	    }

	    return descuentoAplicado;
	}

}
