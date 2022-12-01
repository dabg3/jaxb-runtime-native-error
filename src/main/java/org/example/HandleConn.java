package org.example;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
        name = "HandleConn"
)
public class HandleConn {

    private String sHandle;

    public HandleConn() {
    }

    public String getSHandle() {
        return this.sHandle;
    }

    public void setSHandle(String handle) {
        this.sHandle = handle;
    }

}
