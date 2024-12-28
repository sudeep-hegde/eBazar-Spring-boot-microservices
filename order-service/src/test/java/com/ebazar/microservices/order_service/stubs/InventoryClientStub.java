package com.ebazar.microservices.order_service.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * InventoryClientStub to stub the inventory service call for integration testing,
 * independently without running inventory service.
 */
public class InventoryClientStub {

    public static void stubInventoryCall(String skuCode, Integer quality) {
        stubFor(get(urlEqualTo("/api/inventory?skuCode=" + skuCode + "&quantity=" + quality))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody("true")));
    }
}
