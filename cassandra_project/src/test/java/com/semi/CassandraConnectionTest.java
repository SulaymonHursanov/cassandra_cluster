package com.semi;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.junit.After;
import org.junit.Test;

import java.util.Objects;


public class CassandraConnectionTest {
    private Cluster cluster;
    private Session session;

    @Test
    public void connectToNode () {
        String NODE = "54.209.247.105";
        int port = 9042;
        connect(NODE, port);
    }

    private void connect(String node, Integer port) {
        Cluster.Builder builder = Cluster.builder().addContactPoint(node);
        if (Objects.nonNull(port)) {
            builder.withPort(port);
        }

        cluster = builder.build();
        session = cluster.connect();
//        session.execute("select * from airline.timetable");
    }

    public Session getSession() {
        return session;
    }

    @After
    public void close () {
        session.close();
        cluster.close();
    }
}
