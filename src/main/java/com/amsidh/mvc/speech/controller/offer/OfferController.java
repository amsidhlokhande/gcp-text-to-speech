package com.amsidh.mvc.speech.controller.offer;

import com.amsidh.mvc.speech.model.OfferOutputVO;
import com.amsidh.mvc.speech.model.OfferVO;
import com.amsidh.mvc.speech.service.OfferService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OfferOutputVO> dynamicOffer(@RequestBody OfferVO offerVO) {
        log.info("Inside dynamicOffer Method");
        List<OfferOutputVO> offerOutputVO = offerService.dynamicOffer(offerVO);
        return offerOutputVO;
    }


}
