package org.example;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

@SpringBootTest
public class JAXBTest {

    @Test
    public void readXml() throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(HandleConn.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        ClassPathResource file = new ClassPathResource("content.xml");

        HandleConn handle = (HandleConn) unmarshaller.unmarshal(file.getInputStream());

        Assertions.assertThat(handle != null && !handle.getSHandle().isBlank());
        System.out.println("----HANDLE " + handle.getSHandle() + "----");
    }
}
