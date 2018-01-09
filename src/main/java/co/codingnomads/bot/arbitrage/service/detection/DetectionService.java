package co.codingnomads.bot.arbitrage.service.detection;

import co.codingnomads.bot.arbitrage.detectionAction.DifferenceWrapper;
import co.codingnomads.bot.arbitrage.mapper.DetectionWrapperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Kevin Neag
 */
@Service
public class DetectionService {

    @Autowired
    DetectionWrapperMapper mapper;

    public void insertDetectionRecords(DifferenceWrapper differenceWrapper) {

            mapper.insert_DifferenceWrapper(differenceWrapper.getTimestamp(),differenceWrapper.getCurrencyPair(),differenceWrapper.getDifference(),
                    differenceWrapper.getLowAsk(),differenceWrapper.getLowAskExchange(),differenceWrapper.getHighBid(),differenceWrapper.getHighBidExchange());

        }

    }
