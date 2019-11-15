package com.semi;

import com.datastax.driver.core.Session;
import org.junit.Before;
import org.junit.Test;

public class CassandraCreateKeyspaceAndColumnFamilyTest {

    private Session session;

    @Before
    public void setUp() throws Exception {
        CassandraConnectionTest cassandraConnectionTest = new CassandraConnectionTest();
        cassandraConnectionTest.connectToNode();
        session = cassandraConnectionTest.getSession();
    }

    @Test
    public void createKeySpace (){
        createKeyspace("Airline", "SimpleStrategy", 3);
    }

    public void createKeyspace(String keyspaceName, String replicationStrategy, int replicationFactor) {
        StringBuilder sb = new StringBuilder("CREATE KEYSPACE IF NOT EXISTS ")
                        .append(keyspaceName).append(" WITH replication = {")
                        .append("'class':'").append(replicationStrategy)
                        .append("','replication_factor':").append(replicationFactor)
                        .append("};");

        String query = sb.toString();
        session.execute(query);
    }


    @Test
    public void createTable() {
        createTable("airline.TimeTable");
    }

    public void createTable(String tableName) {
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append(tableName).append("(")
                .append("id uuid PRIMARY KEY, ")
                .append("title text,")
                .append("fromCity text,")
                .append("toCity text,")
                .append("departureTime timestamp, ")
                .append("duration int );");

        String query = sb.toString();
        System.out.println(query);
        session.execute(query);
    }

}
