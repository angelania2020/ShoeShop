/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Angelina
 */
@Entity
public class Producer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String producerName;
    private String producerCountry;

    public Producer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public String getProducerCountry() {
        return producerCountry;
    }

    public void setProducerCountry(String producerCountry) {
        this.producerCountry = producerCountry;
    }

    @Override
    public String toString() {
        return "Producer{" + "producerName=" + producerName + ", producerCountry=" + producerCountry + '}';
    }

}
