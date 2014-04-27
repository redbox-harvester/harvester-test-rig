/*******************************************************************************
 * Copyright (C) 2013 Queensland Cyber Infrastructure Foundation (http://www.qcif.edu.au/)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 ******************************************************************************/
package au.com.redboxresearchdata.harvester.testrig.service.jms.consumer.impl;

import au.com.redboxresearchdata.harvester.testrig.service.jms.consumer.PayloadFileService;
import org.apache.commons.io.FileUtils;
import org.springframework.integration.annotation.Header;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Created by mulhollm on 4/27/14.
 */
@Component
public class PayloadFileServiceImpl implements PayloadFileService {

    @ServiceActivator
    public void onPayload(String payload, @Header("outputFile") String outputFile) {
        writeToFile(payload, outputFile);
    }

    private void writeToFile(String payload, String outputFile) {
        File file = new File(outputFile);
        try {
            FileUtils.writeStringToFile(file, payload, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
