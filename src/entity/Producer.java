/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author Angelina
 */
public class Producer implements Serializable {
    private String producerName;
    private String producerCountry;

    public Producer() {
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
