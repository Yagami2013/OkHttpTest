package com.yangtt.hj.okhttptest.Util;

/**
 * Created by hj on 2018/4/26.
 */
import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.IOException;
import java.util.Date;

/**
 * Created by alvin on 16-6-24.
 */
public class MQTTSend {
    private final String TAG="MQTTSend";
    /**
     * 设置当前用户私有的MQTT的接入点。例如此处示意使用XXX，实际使用请替换用户自己的接入点。接入点的获取方法是，在控制台申请MQTT实例，每个实例都会分配一个接入点域名。
     */
    private final String broker ="tcp://kafka-cn-internet.aliyun.com:8080";
    /**
     * 设置阿里云的AccessKey，用于鉴权
     */
    private final String acessKey ="LTAIcSgQ9rjgaDxU";
    /**
     * 设置阿里云的SecretKey，用于鉴权
     */
    private final String secretKey ="XZjpPshnfKWSdzSPctvRJy8t4gEAuZ";
    /**
     * 发消息使用的一级Topic，需要先在MQ控制台里申请
     */
    private final String topic ="alikafka-gsjk-tingyun";
    /**
     * MQTT的ClientID，一般由两部分组成，GroupID@@@DeviceID
     * 其中GroupID在MQ控制台里申请
     * DeviceID由应用方设置，可能是设备编号等，需要唯一，否则服务端拒绝重复的ClientID连接
     */
    private final String clientId ="CID-alikafka-Tingyun@@@ClientID_MI_MIX2_59d21d5d";
    public void send() throws IOException {

        String sign;
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            final MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            final MqttConnectOptions connOpts = new MqttConnectOptions();
            Log.d(TAG,"Connecting to broker: " + broker);
            //System.out.println("Connecting to broker: " + broker);
            /**
             * 计算签名，将签名作为MQTT的password。
             * 签名的计算方法，参考工具类MacSignature，第一个参数是ClientID的前半部分，即GroupID
             * 第二个参数阿里云的SecretKey
             */
            sign = MacSignature.macSignature(clientId.split("@@@")[0], secretKey);
            Log.d(TAG,"sign:"+sign);
            connOpts.setUserName(acessKey);
            connOpts.setServerURIs(new String[] { broker });
            connOpts.setPassword(sign.toCharArray());
            connOpts.setCleanSession(true);
            connOpts.setKeepAliveInterval(90);
            connOpts.setAutomaticReconnect(true);
            sampleClient.setCallback(new MqttCallbackExtended() {
                public void connectComplete(boolean reconnect, String serverURI) {
                    //System.out.println("connect success");
                    Log.d(TAG,"connect success ");
                    //连接成功，需要上传客户端所有的订阅关系
                }
                public void connectionLost(Throwable throwable) {
                    //System.out.println("mqtt connection lost");
                    Log.d(TAG,"mqtt connection lost");
                }
                public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                    Log.d(TAG,"messageArrived:" + topic + "------" + new String(mqttMessage.getPayload()));
                    //System.out.println("messageArrived:" + topic + "------" + new String(mqttMessage.getPayload()));
                }
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                    //System.out.println("deliveryComplete:" + iMqttDeliveryToken.getMessageId());
                    Log.d(TAG,"deliveryComplete:" + iMqttDeliveryToken.getMessageId());
                }
            });
            sampleClient.connect(connOpts);
            for (int i = 0; i < 10; i++) {
                try {
                    String scontent = new Date()+"MQTT Test body" + i;
                    //此处消息体只需要传入byte数组即可，对于其他类型的消息，请自行完成二进制数据的转换
                    final MqttMessage message = new MqttMessage(scontent.getBytes());
                    message.setQos(0);
                    //System.out.println(i+" pushed at "+new Date()+" "+ scontent);
                    Log.d(TAG,i+" pushed at "+new Date()+" "+ scontent);
                    /**
                     *消息发送到某个主题Topic，所有订阅这个Topic的设备都能收到这个消息。
                     * 遵循MQTT的发布订阅规范，Topic也可以是多级Topic。此处设置了发送到二级Topic
                     */
                    sampleClient.publish(topic+"/notice/", message);
                    /**
                     * 如果发送P2P消息，二级Topic必须是“p2p”，三级Topic是目标的ClientID
                     * 此处设置的三级Topic需要是接收方的ClientID
                     */
                    String p2pTopic =topic+"/p2p/GID_mqttdelay3@@@DEVICEID_001";
                    sampleClient.publish(p2pTopic,message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception me) {
            me.printStackTrace();
        }
    }
}

