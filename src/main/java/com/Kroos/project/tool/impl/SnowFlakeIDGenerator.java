package com.Kroos.project.tool.impl;

import com.Kroos.project.tool.IDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SnowFlake算法生成ID
 * @author Kroos
 *
 */
public class SnowFlakeIDGenerator implements IDGenerator {

    private final static Logger log= LoggerFactory.getLogger(SnowFlakeIDGenerator.class);


    /**
     * 开始时间截 (2020-01-18)
     */
    private final long twepoch = 1579359550L;

    /**
     * 机器ID所占的位数
     */
    private final long workerIDBits = 5L;

    /**
     * 数据标识ID所占的位数
     */
    private final long datacenterIDBits = 5L;

    /**
     * 支持的最大机器ID，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private final long maxWorkerID = -1L ^ (-1L << workerIDBits);

    /**
     * 支持的最大数据标识ID，结果是31
     */
    private final long maxDatacenterID = -1L ^ (-1L << datacenterIDBits);

    /**
     * 序列在ID中占的位数
     */
    private final long sequenceBits = 12L;

    /**
     * 机器ID向左移12位
     */
    private final long workerIDShift = sequenceBits;

    /**
     * 数据标识ID向左移17位(12+5)
     */
    private final long datacenterIDShift = sequenceBits + workerIDBits;

    /**
     * 时间截向左移22位(5+5+12)
     */
    private final long timestampLeftShift = sequenceBits + workerIDBits + datacenterIDBits;

    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 工作机器ID(0~31)
     */
    private long workerID;

    /**
     * 数据中心ID(0~31)
     */
    private long datacenterID;

    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;

    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;

    //==============================Constructors=====================================

    /**
     * 构造函数
     *
     * @param workerID     工作ID (0~31)
     * @param datacenterID 数据中心ID (0~31)
     */
    public SnowFlakeIDGenerator(long workerID, long datacenterID) {
        if (workerID > maxWorkerID || workerID < 0) {
            log.error("workerID  %d 小于0",maxWorkerID);
            throw new IllegalArgumentException(String.format("worker ID can't be greater than %d or less than 0", maxWorkerID));
        }
        if (datacenterID > maxDatacenterID || datacenterID < 0) {
            log.error("datacenterID  %d 小于0",maxDatacenterID);
            throw new IllegalArgumentException(String.format("datacenter ID can't be greater than %d or less than 0", maxDatacenterID));
        }
        this.workerID = workerID;
        this.datacenterID = datacenterID;
    }

    // ==============================Methods==========================================

    /**
     * 获得下一个ID
     *
     * @return SnowflakeID
     */
    public synchronized long createID() {
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            log.error("时钟发生回拨. 生成id异常 %d 毫秒", lastTimestamp - timestamp);
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate ID for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }

        //上次生成ID的时间截
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - twepoch) << timestampLeftShift) //
                | (datacenterID << datacenterIDShift) //
                | (workerID << workerIDShift) //
                | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    @Override
    public long nextID() {
        return createID();
    }
//    public static void main(String[] args) {
//        SnowFlakeIDGenerator IDWorker = new SnowFlakeIDGenerator(0, 0);
//        for (int i = 0; i < 1000; i++) {
//            long ID = IDWorker.createID();
////      System.out.println(Long.toBinaryString(ID));
//            System.out.println(ID);
//        }
//    }
}
