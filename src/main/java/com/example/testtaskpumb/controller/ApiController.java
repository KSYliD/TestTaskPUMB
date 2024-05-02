package com.example.testtaskpumb;

import com.example.testtaskpumb.service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class ApiController {

    private AnimalService animalService;

    @GetMapping("/get/{value}")
    public List<Animal> getHelloWorld(@PathVariable String value) {
        List<Animal> list = new ArrayList<>();
        return list;
    }

    @PostMapping("/files/uploads")
    public List<List<String>> postHelloWorld(@RequestBody MultipartFile file) {
        List<List<String>> records = new ArrayList<>();

        return records;
    }

    @PostMapping("/files/upload")
    //TODO: File... file
    public String postelloWorld(@RequestBody MultipartFile file) throws IOException {

        if (file.getContentType().equals("application/xml")) {
            try {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(file.getInputStream());
                doc.getDocumentElement().normalize();
                NodeList nList = doc.getElementsByTagName("animal");

                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;

                        String name = eElement
                                .getElementsByTagName("name")
                                .item(0)
                                .getTextContent();
                        String type = eElement
                                .getElementsByTagName("type")
                                .item(0)
                                .getTextContent();
                        String sex = eElement
                                .getElementsByTagName("sex")
                                .item(0)
                                .getTextContent();
                        Double weight = Double.parseDouble(eElement
                                .getElementsByTagName("weight")
                                .item(0)
                                .getTextContent());
                        Double cost = Double.parseDouble(eElement
                                .getElementsByTagName("cost")
                                .item(0)
                                .getTextContent());
                        Animal animal = null;
                        //TODO: String isEmpty, isBlank
                        if (name != null && type != null && sex != null && weight != null && cost != null) {
                            animal = new Animal(name, type, sex, weight, cost);
                        }
                        System.out.println(animal);
                        animalService.save(animal);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "You successfully uploaded " + file.getOriginalFilename() + "!";
    }
}
