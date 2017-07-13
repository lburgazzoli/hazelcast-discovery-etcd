/*
 * Copyright (c) 2015 Luca Burgazzoli and contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.lburgazzoli.hazelcast.discovery.etcd;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.config.properties.PropertyDefinition;
import com.hazelcast.config.properties.PropertyTypeConverter;
import com.hazelcast.config.properties.SimplePropertyDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EtcdDiscovery {
    private static final Logger LOGGER = LoggerFactory.getLogger(EtcdDiscovery.class);

    public static final ObjectMapper MAPPER =
        new ObjectMapper()
            .registerModule(new AfterburnerModule())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public static final int    DEFAULT_HZ_PORT             = NetworkConfig.DEFAULT_PORT;
    public static final String  DEFAULT_ETCD_URL            = "http://127.0.0.1:2379";
    public static final String  DEFAULT_ETCD_URLS           = "http://127.0.0.1:2379,http://127.0.0.1:4001";
    public static final int     DEFAULT_ETCD_TIMEOUT_SEC    = 5;
    public static final String  DEFAULT_SERVICE_NAME        = "hazelcast";
    public static final boolean DEFAULT_REGISTER_LOCAL_NODE = false;
    public static final String  URLS_SEPARATOR              = ",";

    public static final PropertyDefinition PROPERTY_URLS =
        new SimplePropertyDefinition("urls", PropertyTypeConverter.STRING);
    public static final PropertyDefinition PROPERTY_SERVICE_NAME =
        new SimplePropertyDefinition("serviceName", PropertyTypeConverter.STRING);
    public static final PropertyDefinition PROPERTY_REGISTER_LOCAL_NODE =
        new SimplePropertyDefinition("registerLocalNode", true, PropertyTypeConverter.BOOLEAN);
    public static final PropertyDefinition PROPERTY_LOCAL_NODE_NAME =
        new SimplePropertyDefinition("localNodeName", true, PropertyTypeConverter.STRING);
    public static final PropertyDefinition PROPERTY_TIMEOUT =
        new SimplePropertyDefinition("timeout", true, PropertyTypeConverter.INTEGER);

    // *************************************************************************
    //
    // *************************************************************************

    public static EtcdDiscoveryNode nodeFromString(String value) {
        try {
            return MAPPER.readValue(value, EtcdDiscoveryNode.class);
        } catch(Exception e) {
            LOGGER.warn("", e);
        }

        return null;
    }

    public static String asString(Object value) throws JsonProcessingException {
        return MAPPER.writeValueAsString(value);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable> T getProperty(
            Map<String, Comparable> properties, PropertyDefinition property, T defaultValue) {

        return (T)properties.getOrDefault(property.key(), defaultValue);
    }
}
