package es.unican.is2.Common;
import java.time.LocalDate;
import java.time.Year;

/**
 * Clase que representa un vehiculo de tipo turismo.
 */
public class Turismo extends Vehiculo {

	private double potencia;
	
	public Turismo(long id, String matricula, LocalDate fechaMatriculacion, TipoMotor motor, double potencia) {
		super(id, matricula, fechaMatriculacion, motor);
		
		this.potencia = potencia;
		
	}

	/**
	 * Retorna la potencia en caballos fiscales del vehiculo.
	 */
	public double getPotencia() {
		return potencia;
	}

	@Override
	public double precioImpuesto() {

	    double descuentoAplicado = calcularDescuento();
	    
	    double precioFinal = 0;

	    if (potencia < 10) {
	    	
	        precioFinal = 30;
	    }
	    
	    else if (potencia >= 10 && potencia < 15) {
	    	
	        precioFinal = 72;
	    }
	    
	    else if (potencia >= 15 && potencia < 18) {
	    	
	        precioFinal = 150;
	    }
	    
	    else if (potencia >= 18 && potencia < 25) {
	    	
	        precioFinal = 190;
	    }
	    
	    else if (potencia >= 25) {
	    	
	        precioFinal = 250;
	    }

	    return descuentoAplicado * precioFinal;
	}


}
