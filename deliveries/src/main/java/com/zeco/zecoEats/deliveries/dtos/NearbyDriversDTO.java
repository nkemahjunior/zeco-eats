package com.zeco.zecoEats.deliveries.dtos;

import java.time.LocalDateTime;

public interface NearbyDriversDTO {
        //method names should match database columns name

        Long getAvlDriverId();

        Long getDriverId();

        Object getCoordinates();

        LocalDateTime getHeartBeat();

        String getFcmRegistrationToken();

        Double getDistance();

        Boolean getOnline();

        //toStringCustom because you can not override toString in an interface
        default String toStringCustom() {
                return "NearbyDriversDTO{" +
                        "avlDriverID=" + getAvlDriverId() +
                        ", driverID=" + getDriverId() +
                        ", Coordinates=" + getCoordinates() +
                        ", heartBeat=" + getHeartBeat() +
                        ", online=" + getOnline() +
                        ", fcmRegistrationToken='" + getFcmRegistrationToken() + '\'' +
                        ", distance=" + getDistance() +
                        '}';
        }

}
