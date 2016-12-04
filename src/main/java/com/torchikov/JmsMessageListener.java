package com.torchikov;

import com.torchikov.entity.Organization;
import com.torchikov.entity.Person;
import com.torchikov.parser.Parser;
import com.torchikov.parser.impl.JaxbParser;
import com.torchikov.requests.OrganizationRequest;
import com.torchikov.requests.PersonRequest;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.activemq.util.ByteArrayInputStream;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by sergei on 03.12.16.
 */
@Service
public class JmsMessageListener implements SessionAwareMessageListener<TextMessage> {

    private HashMap<Long, Person> persons = new HashMap<>();
    private HashMap<Long, Organization> organizations = new HashMap<>();

    @Override
    public void onMessage(TextMessage message, Session session) throws JMSException {
        createTestData();
        System.out.println("Receive: " + message.getText());

        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            ByteArrayInputStream inputStream = new ByteArrayInputStream(message.getText().getBytes("UTF-8"));
            Document document = documentBuilder.parse(inputStream);
            inputStream.close();
            document.getDocumentElement().normalize();
            String rootElement = document.getDocumentElement().getNodeName();
            OutputStream outputStream = new ByteArrayOutputStream();

            switch (rootElement) {
                case "personRequest":
                    Parser<PersonRequest, Person> parserPerson = new JaxbParser<>();
                    PersonRequest personRequest = parserPerson.getObject(document.getDocumentElement().getParentNode(), PersonRequest.class);
                    Person person = persons.get(personRequest.getPersonId());
                    parserPerson.saveObject(outputStream, person);
                    sendResponse(outputStream, session);
                    break;
                case "organizationRequest":
                    Parser<OrganizationRequest, Organization> parserOrganization = new JaxbParser<>();
                    OrganizationRequest organizationRequest = parserOrganization.getObject(document.getDocumentElement().getParentNode(), OrganizationRequest.class);
                    Organization organization = organizations.get(organizationRequest.getOrganizationId());
                    parserOrganization.saveObject(outputStream, organization);
                    sendResponse(outputStream, session);
                default:
                    break;
            }
        } catch (ParserConfigurationException | IOException | SAXException | JAXBException e) {
            e.printStackTrace();
        }


    }

    private void sendResponse(OutputStream outputStream, Session session) {
        try {
            ActiveMQTextMessage responseMessage = new ActiveMQTextMessage();
            responseMessage.setText(outputStream.toString());
            outputStream.close();
            MessageProducer producer = session.createProducer(new ActiveMQQueue("OUT"));
            producer.send(responseMessage);
        } catch (JMSException | IOException e) {
            e.printStackTrace();
        }
    }

    private void createTestData() {
        Person john = new Person();
        john.setId(123L);
        john.setName("John");
        john.setAge(27);

        Person mary = new Person();
        mary.setId(23432L);
        mary.setName("Mary");
        mary.setAge(23);
        john.setFriends(Arrays.asList(mary));

        Person mike = new Person();
        mike.setId(980987L);
        mike.setName("Mike");
        mike.setAge(25);
        persons.put(john.getId(), john);
        persons.put(mary.getId(), mary);
        persons.put(mike.getId(), mike);

        Organization cityBuilder = new Organization();
        cityBuilder.setId(5610L);
        cityBuilder.setName("City builder");
        cityBuilder.setITN("21323-5435-1231");

        Organization insurance = new Organization();
        insurance.setId(124587L);
        insurance.setName("Insurance");
        insurance.setITN("123213-54536-213423");

        Organization superMarket = new Organization();
        superMarket.setId(98187621L);
        superMarket.setName("Supermarket");
        superMarket.setITN("123123-12312-1111");
        organizations.put(cityBuilder.getId(), cityBuilder);
        organizations.put(insurance.getId(), insurance);
        organizations.put(superMarket.getId(), superMarket);
    }

}
