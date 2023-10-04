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

private static List<String> readAllLines(String resource)throws IOException{
    ClassLoader classLoader=Thread.currentThread().getContextClassLoader();

    try(InputStream inputStream=classLoader.getResourceAsStream(resource);
        BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream,StandardCharsets.UTF_8))){

        return reader.lines().collect(Collectors.toList());
    }
}
    private final Random random = new Random();
    public static final int randomImage = 10;
    @GetMapping("/")
    public ModelAndView index() throws IOException {

        ModelAndView result = new ModelAndView("index");

        int randomNumber = random.nextInt(randomImage)+1;
        result.addObject("obrazek", String.format("/images/image-%d.jpg", randomNumber));

        List<String> seznam = readAllLines("citaty.txt");

        randomNumber = random.nextInt(seznam.size());
        System.out.println(seznam.size());
        result.addObject("citat",seznam.get(randomNumber));

        return result;
    }
}
