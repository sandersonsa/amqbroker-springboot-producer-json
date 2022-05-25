package xyz.sandersonsa.amqbrokerspringbootproducerjson.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import xyz.sandersonsa.amqbrokerspringbootproducerjson.model.Pessoa;
import xyz.sandersonsa.amqbrokerspringbootproducerjson.service.ProduceService;

@RestController
@RequestMapping("pessoa")
@Slf4j
public class PessoaRestController {

    @Autowired
    private ProduceService produceService;

    @Value("${app.springboot.queue}")
    private String destinationQueue;

    @GetMapping("/send-message/{message}")
    public String sendMessage(@PathVariable String message) {
        produceService.sendTo(destinationQueue, message);
        return "success";
    }

    @PostMapping(value = "", consumes =  MediaType.APPLICATION_JSON_VALUE)
    public void inserirPessoa(@RequestBody Pessoa pessoa) {
        log.info(" # Recebendo pessoa .. :: {}", pessoa);
        log.info(" # Data Nascimento :: {}", pessoa.getDtNascimento().toString());
        produceService.sendTo(destinationQueue, pessoa);
    }
    
}
