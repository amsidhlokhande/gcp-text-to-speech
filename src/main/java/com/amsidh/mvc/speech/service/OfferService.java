package com.amsidh.mvc.speech.service;

import com.amsidh.mvc.speech.model.OfferOutputVO;
import com.amsidh.mvc.speech.model.OfferVO;

import java.util.List;

public interface OfferService {
    List<OfferOutputVO> dynamicOffer(OfferVO offerVO);
}
