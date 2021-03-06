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
package com.github.lburgazzoli.hazelcast.discovery.etcd

import com.fasterxml.jackson.databind.ObjectMapper

class EtcdDiscoveryTestSupport {
    public static final def MAPPER = new ObjectMapper()
    public static final def NODE_NAMES = ['node1', 'node2', 'node3', 'node4' ]
    public static final def NODES = [
        "hazelcast-127.0.0.1-1010" : [
            "host": "127.0.0.1",
            "port": 1010,
            "tags": [
                "name": "hazelcast-127.0.0.1-1010"
            ]
        ],
        "node1" : [
            "host": "127.0.0.1",
            "port": 9001,
            "tags": [
                "name": "node1"
            ]
        ],
        "node2" : [
            "host": "127.0.0.1",
            "port": 9002,
            "tags": [
                "name": "node2"
            ]
        ],
        "node3" : [
            "host": "127.0.0.1",
            "port": EtcdDiscovery.DEFAULT_HZ_PORT,
            "tags": [
                "name": "node3"
            ]
        ],
        "node4" : [
            "host": "127.0.0.1",
            "tags": [
                "name": "node4"
            ]
        ]
    ]
}
