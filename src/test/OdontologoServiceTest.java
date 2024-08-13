package test;

import dao.Impl.OdontologoDaoH2;
import model.Odontologo;
import org.junit.jupiter.api.BeforeAll;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.OdontologoService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {

    public static final Logger logger = Logger.getLogger(OdontologoServiceTest.class);
    OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());


    @BeforeAll
    static void crearTablas(){
        Connection connection = null;
        try{
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./odontologos;INIT=RUNSCRIPT FROM 'create.sql'",
                    "sa", "sa");
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Test
    @DisplayName("Testear que se guarde un odontologo en la db")
    void caso1(){
        Odontologo odontologo = new Odontologo(569, "SEBASTIAN","MENDOZA");
        Odontologo odontologoDesdeDB = odontologoService.guardarOdontologo(odontologo);

        assertNotNull(odontologoDesdeDB.getId());
    }

    @Test
    @DisplayName("Testear que nos muestre todos los odontologos guardados")
    void caso2(){
        List<Odontologo> odontologos = new ArrayList<>();
        odontologos = odontologoService.busarTodos();
        assertFalse(odontologos.isEmpty());
    }



}







