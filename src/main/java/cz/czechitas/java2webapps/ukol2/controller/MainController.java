package cz.czechitas.java2webapps.ukol2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@Controller
public class MainController {

    //Tohle je druhá verze metody. Nakonec načítám ze souboru, je to přehlednější.

//    List<String> seznamTextu=
//            List.of(
//                    "Debugging /de·bugh·ing/ (verb): The Classic Mystery Game where you are the detective, the victim, and the murderer.",
//                    "A user interface is like a joke. If you have to explain it, it's not that good.",
//                    "To replace programmers with robots, clients will have to accurately describe what they want. We're safe."
//                    ,"I have a joke on programming but it only works on my computer.",
//                    "99 little bugs in the code, 99 bugs in the code. Take one down, patch it around. 127 little bugs in the code…",
//                    "When I wrote this code, only God & I understood what it did. Now… Only God knows.",
//                    "Programmer (noun.): A machine that turns coffee into code.",
//                    "Real programmers count from 0." );
private static List<String> readAllLines(String resource)throws IOException{
    //Soubory z resources se získávají pomocí classloaderu. Nejprve musíme získat aktuální classloader.
    ClassLoader classLoader=Thread.currentThread().getContextClassLoader();

    //Pomocí metody getResourceAsStream() získáme z classloaderu InpuStream, který čte z příslušného souboru.
    //Následně InputStream převedeme na BufferedRead, který čte text v kódování UTF-8
    try(InputStream inputStream=classLoader.getResourceAsStream(resource);
        BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream,StandardCharsets.UTF_8))){

        //Metoda lines() vrací stream řádků ze souboru. Pomocí kolektoru převedeme Stream<String> na List<String>.
        return reader.lines().collect(Collectors.toList());
    }
}

    private final Random random = new Random();
    @GetMapping("/")
    public ModelAndView index() throws IOException {

        int nahodneCislo = random.nextInt(10) + 1;
        int nahodneCisloCitat = random.nextInt(17);

        ModelAndView result = new ModelAndView("index");

        List<String> seznam = readAllLines("citaty.txt");

        result.addObject("obrazek", String.format("/images/image-%d.jpg", nahodneCislo));
        result.addObject("citat",seznam.get(nahodneCisloCitat));

        return result;

    }

}
