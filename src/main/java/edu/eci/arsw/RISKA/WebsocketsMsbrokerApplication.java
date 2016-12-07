package edu.eci.arsw.RISKA;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eci.arsw.RISKA.modelo.Partida;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.google.gson.Gson;

@SpringBootApplication
public class WebsocketsMsbrokerApplication {

	public static void main(String[] args) throws Exception{
            SpringApplication.run(WebsocketsMsbrokerApplication.class, args);
	}
}
