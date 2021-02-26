package com.amsidh.mvc.speech.service.impl;

import com.amsidh.mvc.speech.model.OfferOutputVO;
import com.amsidh.mvc.speech.model.OfferVO;
import com.amsidh.mvc.speech.service.OfferService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    @Value("${OFFERS_DATA_FILE_PATH:C:\\Users\\amsid\\Documents\\workspace-spring-tool-suite-4-4.9.0.RELEASE\\GCP-Text-To-Speech\\src\\main\\resources\\offerRule.csv}")
    private String OFFERS_DATA_FILE_PATH;

    @Override
    public List<OfferOutputVO> dynamicOffer(OfferVO offerVO) {
        String line;
        String splitBy = ",";
        List<OfferOutputVO> outputVOList = new ArrayList<>();
        OfferOutputVO offerOutputVO;
        try {
            BufferedReader br = new BufferedReader(new FileReader(OFFERS_DATA_FILE_PATH));
            while ((line = br.readLine()) != null) {
                String[] offers = line.split(splitBy);    // use comma as separator
                if (offerVO.getGender().equalsIgnoreCase(offers[0])) {
                    offerOutputVO = new OfferOutputVO();
                    offerOutputVO.setOfferDetail(offers[1]);
                    outputVOList.add(offerOutputVO);
                }
            }
        } catch (IOException e) {
            System.out.println("error " + e);
        }
        return outputVOList;
    }


}
