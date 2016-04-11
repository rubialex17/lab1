package hello;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

@Controller
public class GreetingController {

    private SimpMessagingTemplate template;
   

    /*@MessageMapping("/hello")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(3000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }*/
    
    @Autowired
    public GreetingController(SimpMessagingTemplate template) {
        this.template = template;
    }
    @MessageMapping("/hello")
    public void greeting(HelloMessage greeting) throws InterruptedException {
    	Tweet tuit=new Tweet(1, "idStr", "text", new Date(), "fromUser", "profileImageUrl", (long) 1, 1, "languageCode", "source");
        Thread.sleep(1000);
        this.template.convertAndSend("/topic/"+greeting.getName(), tuit);
        Thread.sleep(1000);
        this.template.convertAndSend("/topic/"+greeting.getName(), tuit);
    }


}