package com.dtos.demo.Events;

/*
* The order event structure to send to the other services
* */
public class OrderEvent {

    private Long orderId;
    private Long prodId;
    private int prodqnt;

    public OrderEvent(Long orderId, Long prodId, int prodqnt) {
        this.orderId = orderId;
        this.prodId = prodId;
        this.prodqnt = prodqnt;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public void setProdqnt(int prodqnt) {
        this.prodqnt = prodqnt;
    }


    public Long getOrderId() {
        return orderId;
    }

    public Long getProdId() {
        return prodId;
    }

    public int getProdqnt() {
        return prodqnt;
    }

}
