package com.hk.kafka;

import com.alibaba.fastjson.JSON;
import com.hk.kafka.elem.CountByServiceId;
import com.hk.kafka.entity.AuditLog;
import com.hk.kafka.entity.InvokeLog;
import org.apache.flink.api.common.functions.*;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.functions.AssignerWithPunctuatedWatermarks;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import javax.annotation.Nullable;
import java.util.Properties;

/**
 * Created by kunhe on 12/12/18.
 */
public class Reader {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "172.20.33.8:19092,172.20.33.8:29092,172.20.33.8:39092");
        properties.setProperty("group.id", "kun_pc");
        //properties.setProperty("group.id", "kun_test_devSvrQuota_1");


        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //Set to event time
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        FlinkKafkaConsumer auditConsumer = new FlinkKafkaConsumer<>("devSvrQuota", new SimpleStringSchema(), properties);
        /*
        auditConsumer.assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarks() {
            long lastTs = Long.MIN_VALUE;
            @Nullable
            @Override
            public Watermark getCurrentWatermark() {
                return new Watermark(lastTs);
            }

            @Override
            public long extractTimestamp(Object element, long previousElementTimestamp) {
                long ts = 0L;

                if(element != null)
                {
                    String val = (String)element;
                    AuditLog ret = JSON.parseObject(val, AuditLog.class);
                    if(ret !=null)
                    {
                       ts = ret.getEndts();
                       lastTs = ts;
                    }
                }
                return ts;
            }
        });
        */


        auditConsumer.assignTimestampsAndWatermarks(new AssignerWithPunctuatedWatermarks() {
            private long getTS(String element)
            {
                long ts = Long.MIN_VALUE;

                if(element != null)
                {
                    String val = (String)element;
                    AuditLog ret = JSON.parseObject(val, AuditLog.class);
                    if(ret !=null)
                    {
                        ts = ret.getEndts();
                    }
                }

                return ts;
            }

            @Nullable
            @Override
            public Watermark checkAndGetNextWatermark(Object lastElement, long extractedTimestamp) {

                long lastTs = getTS((String)lastElement);
                long tsForWaterMark = Math.max(lastTs,extractedTimestamp);
                return new Watermark(tsForWaterMark);
            }

            @Override
            public long extractTimestamp(Object element, long previousElementTimestamp) {

                return getTS((String)element);
            }
        });

        FlinkKafkaConsumer invokeConsumer = new FlinkKafkaConsumer<>("invokeKun", new SimpleStringSchema(), properties);

        auditConsumer.setStartFromEarliest();
        invokeConsumer.setStartFromEarliest();

        //env.enableCheckpointing(10);
        DataStream<String> auditStream = env.addSource(auditConsumer);
        DataStream<String> invokeStream = env.addSource(invokeConsumer);

        auditStream.map(new MapFunction<String, CountByServiceId>() {
            @Override
            public CountByServiceId map(String value) throws Exception {
                AuditLog ret = JSON.parseObject(value, AuditLog.class);
                if(ret != null) {
                    CountByServiceId cret = new CountByServiceId();
                    cret.setServiceId(ret.getServiceid());
                    cret.setCount(1);
                    cret.setAuditLog(ret);
                //    System.out.println(cret);
                    return cret;
                }
                else {
                    System.out.println("null auditLog!");
                    return null;
                }
            }
        }).filter(new FilterFunction<CountByServiceId>() {
            @Override
            public boolean filter(CountByServiceId value) throws Exception {
                return value!=null;
            }
        }).keyBy("serviceId").timeWindow(Time.seconds(30)).reduce(new ReduceFunction<CountByServiceId>() {
            @Override
            public CountByServiceId reduce(CountByServiceId value1, CountByServiceId value2) throws Exception {
               // System.out.println("Reduce --- "+value1);
               // System.out.println("Reduce --- "+value2);
                CountByServiceId ret = new CountByServiceId();
                ret.setCount(value1.getCount()+value2.getCount());
                ret.setServiceId(value1.getServiceId());
                return ret;
            }
        })
        .addSink(
                new SinkFunction<CountByServiceId>() {
                    @Override
                    public void invoke(CountByServiceId value, Context context) throws Exception {
                        System.out.println(value.getServiceId()+"---" +value.getCount());
                    }
                }
        );




        invokeStream.map(
                new MapFunction<String, InvokeLog>() {
                    @Override
                    public InvokeLog map(String value) throws Exception {
                        InvokeLog ret = JSON.parseObject(value, InvokeLog.class);
                        return ret;
                    }
                }
        ).filter(new FilterFunction<InvokeLog>() {
            @Override
            public boolean filter(InvokeLog value) throws Exception {
                return value!= null;
            }
        }).addSink(
                new SinkFunction<InvokeLog>() {
                    @Override
                    public void invoke(InvokeLog value, Context context) throws Exception {
                        System.out.println(value.getRequestId());
                    }
                }
        );
        /*
        DataStream<AuditLog> quotaRecords2  = input.map(
                new RichMapFunction<String, AuditLog>() {
                    @Override
                    public AuditLog map(String value) throws Exception {
                        AuditLog ret = JSON.parseObject(value, AuditLog.class);
                        return ret;
                    }
                }
        );
        */

        //KeyedStream<AuditLog,Tuple> recordsByUserid = quotaRecords.keyBy("userid");

/*
        recordsByUserid.reduce(
                new ReduceFunction<AuditLog>() {
                    @Override
                    public Integer reduce(AuditLog value1, AuditLog value2) throws Exception {
                        value1.getInfoCount();
                        return ;
                    }
                }
        );
        */





        try {
            env.execute("Read kafka topic ");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
