package org.example;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.StringReader;

@SpringBootTest
public class JAXBTest {

    @Test
    public void readXml() throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(HandleConn.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        /* ClassPathResource is likely to miss hints as well.
         * ClassPathResource file = new ClassPathResource("content.xml");
         * replacing with StringReader as seen in:
         * https://github.com/oracle/graalvm-reachability-metadata/blob/master/tests/
         */
        StringReader reader = new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><HandleConn><SHandle>0000</SHandle></HandleConn>");

        HandleConn handle = (HandleConn) unmarshaller.unmarshal(reader);

        Assertions.assertThat(handle != null && !handle.getSHandle().isBlank());
        System.out.println("----HANDLE " + handle.getSHandle() + "----");
    }
}
