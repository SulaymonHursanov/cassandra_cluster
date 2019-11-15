package com.semi;

import com.datastax.driver.core.utils.UUIDs;
import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class CassandraCalculateTPSTest {

    private CassandraWriteDataTest cassandraWriteDataTest;

    @Before
    public void setUp() throws Exception {
        cassandraWriteDataTest = new CassandraWriteDataTest();
        cassandraWriteDataTest.setUp();
    }

    @Test
    public void calculateTransactionPerSecondTest () {
        Faker faker = new Faker(new Locale("en"));
        List<TimeTable> timeTables = getTimeTables(faker);

        System.out.println("got timetable size: "+ timeTables.size());

        long start = System.currentTimeMillis();
        timeTables.forEach(timeTable -> cassandraWriteDataTest.insertTimeTable(timeTable));
        long end = System.currentTimeMillis();
        long x = end - start;
        System.out.println(x);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(x), ZoneId.systemDefault());
        System.out.println(localDateTime);

    }

    private List<TimeTable> getTimeTables(Faker faker) {
        List<TimeTable> airlineTimeTables = new LinkedList<>();
        for (int count = 0; count < 100_000; count++) {
            TimeTable timeTable = new TimeTable();
            timeTable.setToCity(faker.address().city().replace("\'", ""));
            timeTable.setFromCity(faker.address().city().replace("\'", ""));
            timeTable.setDuration(faker.number().numberBetween(20, 600));
            timeTable.setTitle(faker.aviation().aircraft().replace("\'", ""));
            timeTable.setId(UUIDs.timeBased());
            airlineTimeTables.add(timeTable);
//            System.out.println(timeTable.toString());
        }
        return airlineTimeTables;
    }
}
