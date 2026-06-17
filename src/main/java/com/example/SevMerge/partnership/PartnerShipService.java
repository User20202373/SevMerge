package com.example.SevMerge.partnership;

import com.example.SevMerge.core.exception.BadRequestException;
import com.example.SevMerge.portfolio.utile.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PartnerShipService {

    private final PartnerShipRepository partnerShipRepository;
    private final PartnerShipMailService partnerShipMailService;

    public void save(PartnerShipRequest request) {
        // 스크립트에서 validate 처리
        try {
            partnerShipMailService.sendPartnerShipMail(request);

            PartnerShip partnerShip = PartnerShip
                    .builder()
                    .companyName(request.getCompanyName())
                    .managerName(request.getManagerName())
                    .email(request.getEmail())
                    .partnerFileUrl(FileUtil.partnerFileSave(request.getPartnerFile()))
                    .content(request.getContent())
                    .status(PartnerShipStatus.PENDING)
                    .build();
            partnerShipRepository.save(partnerShip);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 제휴목록
    public List<PartnerShipResponse> list(){
       List<PartnerShip> partnerShipList = partnerShipRepository.findAll();
       return partnerShipList.stream().map(PartnerShipResponse::new).toList();
    }

    // 승인 거절 할 제휴 데이터 찾기

    public PartnerShip findById(Long id){
        return partnerShipRepository.findById(id).orElseThrow(() ->
                    new BadRequestException("해당하는 제휴가 없습니다.")
                );
    }

}
