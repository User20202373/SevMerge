package com.example.SevMerge.partnership;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PartnerShipResponse {

    private Long id; // 제휴문의 아이디
    private String companyName;
    private String email;
    private String managerName;
    private Timestamp createdAt;
    private PartnerShipStatus status;


    public PartnerShipResponse(PartnerShip partnerShip) {
        this.id = partnerShip.getId();
        this.companyName = partnerShip.getCompanyName();
        this.email = partnerShip.getEmail();
        this.managerName = partnerShip.getManagerName();
        this.createdAt = partnerShip.getCreatedAt();
        this.status = partnerShip.getStatus();
    }

    public boolean isApproved(){
      return status == PartnerShipStatus.APPROVED;
    }
    public boolean isPending(){
        return status == PartnerShipStatus.PENDING;
    }
    public boolean isRejected(){
        return status == PartnerShipStatus.REJECTED;
    }
}
