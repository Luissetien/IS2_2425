package es.unican.is2.Common;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VehiculoTest {

    private Motocicleta moto;
    private Turismo turismo;

    @BeforeEach
    void setUp() {
        moto = new Motocicleta(1, "1111AAA", LocalDate.of(2020, 1, 1), TipoMotor.GASOLINA, 150);
        turismo = new Turismo(2, "2222BBB", LocalDate.of(2015, 5, 20), TipoMotor.ELECTRICO, 12);
    }

    @Test
    void testPrecioImpuestoMotocicleta() {
        assertEquals(18, moto.precioImpuesto(), 0.01, "Motocicleta con 150cc debe pagar 18");
        
        moto = new Motocicleta(3, "3333CCC", LocalDate.of(2000, 1, 1), TipoMotor.GASOLINA, 900);
        assertEquals(150, moto.precioImpuesto(), 0.01, "Motocicleta con 900cc debe pagar 150");
    }

    @Test
    void testPrecioImpuestoTurismo() {
        assertEquals(72 * 0.8, turismo.precioImpuesto(), 0.01, "Turismo eléctrico con 12cv debe pagar 72 con 20% de descuento");
        
        turismo = new Turismo(4, "4444DDD", LocalDate.of(2010, 6, 15), TipoMotor.DIESEL, 25);
        assertEquals(250, turismo.precioImpuesto(), 0.01, "Turismo con 25cv debe pagar 250");
    }
    
    @Test
    void testCalcularDescuento() {
        Vehiculo v1 = new Turismo(5, "5555EEE", LocalDate.of(1990, 3, 10), TipoMotor.GASOLINA, 20);
        assertEquals(1, v1.calcularDescuento(), "Vehículo con más de 21 años no tiene descuento");
        
        Vehiculo v2 = new Turismo(6, "6666FFF", LocalDate.of(2023, 1, 1), TipoMotor.GAS, 10);
        assertEquals(0.6, v2.calcularDescuento(), "Vehículo a gas con menos de 2 años tiene 40% de descuento");
    }
}
