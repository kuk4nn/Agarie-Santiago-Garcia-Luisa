package test;

import dao.Impl.OdontologoDaoH2;
import dao.Impl.OdontologoDaoH2Memoria;
import model.Odontologo;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.OdontologoService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTestMemoria {

    public static final Logger logger = Logger.getLogger(OdontologoServiceTestMemoria.class);
    OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2Memoria());

    @Test
    @DisplayName("Testear que un odontologo se guarde en la DB")
    void caso1(){
        Odontologo odontologo = new Odontologo(268, "MARTINA","LOPEZ");
        Odontologo odontologoDesdeMemoria = odontologoService.guardarOdontologo(odontologo);

        assertNotNull(odontologoDesdeMemoria.getId());

    }

    @Test
    @DisplayName("Mostrar todos odontologos guardados")
    void caso2(){
        List<Odontologo> odontologos;

        Odontologo odontologo1 = new Odontologo(268, "MARTINA","LOPEZ");
        Odontologo odontologo2 = new Odontologo(315, "JUAN","PEREZ");

        odontologoService.guardarOdontologo(odontologo1);
        odontologoService.guardarOdontologo(odontologo2);

        odontologos = odontologoService.busarTodos();

        assertFalse(odontologos.isEmpty());

    }

}