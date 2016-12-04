package com.torchikov.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Created by root on 21.11.16.
 */
@XmlRootElement(name = "person")
@XmlType(propOrder = {
    "id", "name", "age", "friends"
})
public class Person {
    private long id;
    private String name;
    private int age;
    private List<Person> friends;

    public Person() {
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    @XmlElement
    public void setAge(int age) {
        this.age = age;
    }

    public List<Person> getFriends() {
        return friends;
    }

    @XmlElement
    @XmlElementWrapper
    public void setFriends(List<Person> friends) {
        this.friends = friends;
    }


    public long getId() {
        return id;
    }

    @XmlElement(required = true)
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String res = "Person{" +
                "id=" + id + '\'' +
                "name='" + name + '\'' +
                ", age=" + age + ", friends{";
        if (friends != null) {
            for (Person p : friends) {
                res += p.toString();
            }
        }
        res += "}}";
        return res;
    }
}
