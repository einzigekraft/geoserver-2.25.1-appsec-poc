/* (c) 2020 Open Source Geospatial Foundation - all rights reserved
 * This code is licensed under the GPL 2.0 license, available at the root
 * application directory.
 */
package org.geoserver.taskmanager.external;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.geoserver.taskmanager.external.impl.AWSFileServiceImpl;
import org.geotools.util.logging.Logging;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Ignore;

@Ignore
public class AWSFileServiceDataTest extends AbstractS3FileServiceDataTest {

    private static final Logger LOGGER = Logging.getLogger(AWSFileServiceDataTest.class);

    @BeforeClass
    public static void setCredentials() {
        System.setProperty("aws.accessKeyId", "FAKE-TEST-ACCESS-KEY-ID");
        System.setProperty("aws.secretKey", "FAKE-TEST-SECRET-KEY-VALUE");
    }

    @Override
    protected AWSFileServiceImpl getS3FileService() {
        AWSFileServiceImpl service = new AWSFileServiceImpl("taskmanagertest", false, null);
        List<String> folders = null;
        try {
            folders = service.listSubfolders();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        Assume.assumeNotNull(folders);
        return service;
    }
}
