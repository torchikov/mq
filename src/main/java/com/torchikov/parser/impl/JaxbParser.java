package com.torchikov.parser.impl;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import com.torchikov.parser.Parser;
import org.w3c.dom.Node;

import javax.xml.bind.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by root on 21.11.16.
 */
public class JaxbParser implements Parser {
    @Override
    public Object getObject(Node node, Class<?> clazz) throws JAXBException {
        ByteOutputStream output = new ByteOutputStream();
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object object = unmarshaller.unmarshal(node);
        return object;
    }

    @Override
    public void saveObject(OutputStream os, Object object) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(object, os);
    }
}
