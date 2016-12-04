package com.torchikov.parser;

import org.w3c.dom.Node;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by root on 21.11.16.
 */
public interface Parser {
    Object getObject(Node node, Class<?> clazz) throws JAXBException;
    void saveObject(OutputStream os, Object object) throws JAXBException;
}
