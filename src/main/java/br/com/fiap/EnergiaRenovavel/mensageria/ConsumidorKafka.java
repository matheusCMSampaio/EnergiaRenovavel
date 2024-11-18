package br.com.fiap.EnergiaRenovavel.mensageria;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumidorKafka {
    @KafkaListener(topics = ProdutorKafka.topico, groupId = "DirtyCode")
    public void lerMensagem(String mensagem){
        System.out.println("Mensagem recebida: "+ mensagem);
    }
}
