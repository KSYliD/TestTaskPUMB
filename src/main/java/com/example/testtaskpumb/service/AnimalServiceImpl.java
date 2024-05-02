package com.example.testtaskpumb.service;

import com.example.testtaskpumb.entity.Animal;
import com.example.testtaskpumb.entity.Category;
import com.example.testtaskpumb.repository.AnimalRepository;
import com.opencsv.CSVReader;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private AnimalRepository animalRepository;

    @Override
    public void loadFiles(MultipartFile... files) {
        for (MultipartFile file : files) {
            if (Objects.equals(file.getContentType(), "application/xml")) {
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
                            Node nameItem = eElement.getElementsByTagName("name").item(0);
                            Node typeItem = eElement.getElementsByTagName("type").item(0);
                            Node sexItem = eElement.getElementsByTagName("sex").item(0);
                            Node weightItem = eElement.getElementsByTagName("weight").item(0);
                            Node costItem = eElement.getElementsByTagName("cost").item(0);

                            if (nameItem != null && typeItem != null &&
                                    sexItem != null && weightItem != null && costItem != null) {
                                Animal animal = new Animal(nameItem.getTextContent(),
                                        typeItem.getTextContent(),
                                        sexItem.getTextContent(),
                                        Double.parseDouble(weightItem.getTextContent()),
                                        Double.parseDouble(costItem.getTextContent()));
                                animalRepository.save(animal);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (Objects.equals(file.getContentType(), "text/csv")) {
                try {

                    CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()));
                    String[] nextRecord;

                    while ((nextRecord = csvReader.readNext()) != null) {
                        String name = nextRecord[0];
                        String type = nextRecord[1];
                        String sex = nextRecord[2];
                        double weight;
                        double cost;
                        try {
                            weight = Double.parseDouble(nextRecord[3]);
                            cost = Double.parseDouble(nextRecord[4]);
                        } catch (NumberFormatException ex) {
                            continue;
                        }

                        if (name != null && !name.isBlank()
                                && type != null && !type.isBlank()
                                && sex != null && !sex.isBlank()) {
                            Animal animal = new Animal(name, type, sex, weight, cost);
                            animalRepository.save(animal);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Animal> getFilteredAnimals(String type, Category category, String sex, String sortBy, String sortDirection) {
        List<Animal> animals;
        Sort sort;
        if (Objects.equals(sortDirection, "desc")) {
            sort = Sort.by(sortBy).descending();
        } else {
            sort = Sort.by(sortBy).ascending();
        }

        animals = getFilteredAnimals(type, category, sex, sort);
        return animals;
    }

    private List<Animal> getFilteredAnimals(String type, Category category, String sex, Sort sort) {
        Specification<Animal> spec = (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (type != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("type"), type));
            }
            if (category != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("category"), category));
            }
            if (sex != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("sex"), sex));
            }
            return predicate;
        };
        return animalRepository.findAll(spec, sort);
    }
}
