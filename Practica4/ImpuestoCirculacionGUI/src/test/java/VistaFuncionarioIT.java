import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import es.unican.is2.Common.*;
import es.unican.is2.GUI.VistaFuncionario;

import java.time.LocalDate;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.*;
import java.util.Arrays;
import static org.mockito.Mockito.*;

public class VistaFuncionarioIT {
    
    private Vehiculo vehiculoElectrico;
    private Vehiculo vehiculoHibrido;
    private Vehiculo vehiculoGas;
    private Vehiculo vehiculoAntiguo;
    
    @Before
    public void setUp() {
        vehiculoElectrico = new Vehiculo(1, "1234ABC", LocalDate.of(2020, 1, 1), TipoMotor.ELECTRICO) {
            public double precioImpuesto() { return 100.0; }
        };
        vehiculoHibrido = new Vehiculo(2, "5678DEF", LocalDate.of(2022, 1, 1), TipoMotor.HIBRIDO) {
            public double precioImpuesto() { return 100.0; }
        };
        vehiculoGas = new Vehiculo(3, "9012GHI", LocalDate.of(2024, 1, 1), TipoMotor.GAS) {
            public double precioImpuesto() { return 100.0; }
        };
        vehiculoAntiguo = new Vehiculo(4, "3456JKL", LocalDate.of(2000, 1, 1), TipoMotor.DIESEL) {
            public double precioImpuesto() { return 100.0; }
        };
    }
    
    @Test
    public void testCalcularDescuento() {
        assertEquals(0.8, vehiculoElectrico.calcularDescuento(), 0.01);
        assertEquals(0.7, vehiculoHibrido.calcularDescuento(), 0.01);
        assertEquals(0.6, vehiculoGas.calcularDescuento(), 0.01);
        assertEquals(1.0, vehiculoAntiguo.calcularDescuento(), 0.01);
    }
    
    @Test
    public void testVistaFuncionario() throws DataAccessException {
        IInfoImpuestoCirculacion mockInfo = mock(IInfoImpuestoCirculacion.class);
        Contribuyente mockContribuyente = mock(Contribuyente.class);
        when(mockInfo.contribuyente("12345678A")).thenReturn(mockContribuyente);
        when(mockContribuyente.getNombre()).thenReturn("Juan");
        when(mockContribuyente.getApellido1()).thenReturn("Pérez");
        when(mockContribuyente.getApellido2()).thenReturn("Gómez");
        when(mockContribuyente.getVehiculos()).thenReturn(Arrays.asList(vehiculoElectrico, vehiculoHibrido));
        when(mockContribuyente.totalImpuestoCirculacion()).thenReturn(150.0);
        
        VistaFuncionario vista = GuiActionRunner.execute(new GuiQuery<VistaFuncionario>() {
            protected VistaFuncionario executeInEDT() {
                return new VistaFuncionario(mockInfo);
            }
        });


        FrameFixture frame = new FrameFixture(new VistaFuncionario(mockInfo));
        frame.show();
        
        frame.textBox("txtDniContribuyente").enterText("12345678A");
        frame.button("btnBuscar").click();
        frame.textBox("txtNombreContribuyente").requireText("Juan Gómez Pérez");
        frame.textBox("txtTotalContribuyente").requireText("150.00");
        frame.list("listMatriculasVehiculos").requireItemCount(2);
        frame.cleanUp();
    }
}

