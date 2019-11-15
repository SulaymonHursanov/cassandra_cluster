package com.semi;

import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.UUIDs;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class CassandraWriteDataTest {

    private Session session;

    @Before
    public void setUp() throws Exception {
        CassandraConnectionTest cassandraConnectionTest = new CassandraConnectionTest();
        cassandraConnectionTest.connectToNode();
        session = cassandraConnectionTest.getSession();
    }

    @Test
    public void insertbookByTitle() {
        TimeTable timeTable = new TimeTable();
        timeTable.setId(UUIDs.timeBased());
        timeTable.setTitle("S7 airline");
        timeTable.setDepartureTime(new Date());
        timeTable.setDuration(60);
        timeTable.setFromCity("Kazan");
        timeTable.setToCity("Moscow");

        insertTimeTable(timeTable);

    }

    public void insertTimeTable(TimeTable timeTable) {
        StringBuilder sb = new StringBuilder("INSERT INTO ")
                .append("airline.TimeTable").append("(id, title, duration, fromCity, toCity) ")
                .append("VALUES (").append(timeTable.getId())
                .append(", '").append(timeTable.getTitle())
                .append("', ").append(timeTable.getDuration())
                .append(", '").append(timeTable.getFromCity())
                .append("', '").append(timeTable.getToCity())
                .append("');");

        String query = sb.toString();
//        System.out.println(query);
        session.execute(query);
    }


}
