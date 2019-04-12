package com.hk.operators;

import com.hk.operators.entities.Item;
import com.hk.operators.entities.OrderItem;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * Created by kunhe on 12/29/18.
 */
public class OperatorTest {
    public static void main(String[] args) {
        Random rnd = new Random();
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Collection<OrderItem> input = new ArrayList<>();
        OrderItem o1 = new OrderItem();
        o1.setTs(System.currentTimeMillis()+rnd.nextInt()%5000);
        o1.setCatalog(1);
        o1.setName("apple");
        o1.setPrice(2.23F);
        input.add(o1);

        /*
        input.add(new OrderItem().setCatalog(1).setName("apple").setPrice(1.5F).setTs(System.currentTimeMillis()+rnd.nextInt()%5000));
        input.add(new OrderItem().setCatalog(1).setName("banana").setPrice(2.5F).setTs(System.currentTimeMillis()+rnd.nextInt()%5000));
        input.add(new OrderItem().setCatalog(1).setName("pear").setPrice(10.0F).setTs(System.currentTimeMillis()+rnd.nextInt()%5000));
        input.add(new OrderItem().setCatalog(2).setName("pork").setPrice(15.5F).setTs(System.currentTimeMillis()+rnd.nextInt()%5000));
        input.add(new OrderItem().setCatalog(2).setName("beef").setPrice(31.5F).setTs(System.currentTimeMillis()+rnd.nextInt()%5000));
        input.add(new OrderItem().setCatalog(3).setName("coffee").setPrice(101.0F).setTs(System.currentTimeMillis()+rnd.nextInt()%5000));

*/
        DataStreamSource<OrderItem> source = env.fromCollection(input);

        source.map(new MapFunction<OrderItem, OrderItem>() {
                       @Override
                       public OrderItem map(OrderItem value) throws Exception {
                           OrderItem o = new OrderItem(value);
                           o.setUser("kun");
                           return o;
                       }
                   }
        ).keyBy("price").addSink(new SinkFunction<OrderItem>() {
            @Override
            public void invoke(OrderItem value, Context context) throws Exception {
                System.out.println(value.toString() + context.timestamp());
            }
        });
        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
