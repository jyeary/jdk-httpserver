/*
 * Copyright 2018 John Yeary <jyeary@bluelotussoftware.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bluelotusssoftware.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/**
 * An example {@literal "Hello world"} application using the embedded JDK HTTP
 * Server.
 *
 * @author John Yeary <jyeary@bluelotussoftware.com>
 * @version 1.0.0
 */
public class Main {

    /**
     * Default Constructor.
     */
    public Main() {
    }

    /**
     * Main entry point of execution.
     *
     * @param args Arguments to be passed to the application.
     * @throws Exception If any issue occurs while trying to start the server.
     */
    public static void main(String... args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/hello", new HandlerImpl());
        server.setExecutor(null);
        server.start();
    }

    /**
     * An implementation of {@link HttpHandler} that handles requests to the
     * server.
     */
    static class HandlerImpl implements HttpHandler {

        /**
         * {@inheritDoc}
         */
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Hello World from JDK HTTP Server!\n";
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }

}
