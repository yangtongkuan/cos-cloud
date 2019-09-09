package com.cos.cloud.user.web.controller;

import com.cos.cloud.common.config.activemq.JmsNotifyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;


/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/8/9 14:38
 * @Classname: ActiveMqController
 * @To change this template use File | Settings | File Templates.
 */
@RestController
public class ActiveMqController {

    @Autowired
    private Queue queue;


    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @RequestMapping("send/queue")
    public void sendQueue(String name) {
        //方法一：添加消息到消息队列
        JmsNotifyMessage jmsNotifyMessage = new JmsNotifyMessage().setCode("test").setMessage(name);
        jmsMessagingTemplate.convertAndSend(this.queue, jmsNotifyMessage);
        //方法二：这种方式不需要手动创建queue，系统会自行创建名为test的队列
        //jmsMessagingTemplate.convertAndSend("test", name);
    }

    @Autowired
    private Topic topic;

    @RequestMapping("send/top")
    public void sendTop(String name) {
        //方法一：添加消息到消息队列
        JmsNotifyMessage jmsNotifyMessage = new JmsNotifyMessage().setCode("test").setMessage(name);
        jmsMessagingTemplate.convertAndSend(this.topic, jmsNotifyMessage);
        //方法二：这种方式不需要手动创建queue，系统会自行创建名为test的队列
        //jmsMessagingTemplate.convertAndSend("test", name);
    }


}
