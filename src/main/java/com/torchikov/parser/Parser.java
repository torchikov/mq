package com.torchikov.parser;

import org.w3c.dom.Node;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by root on 21.11.16.
 */
public interface Parser<T1, T2> {
    T1 getObject(Node node, Class<T1> clazz) throws JAXBException;
    void saveObject(OutputStream os, T2 object) throws JAXBException;
}
