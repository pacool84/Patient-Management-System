package com.pm.billing_service.grpc;

import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Exposes BillingService RPCs via Spring Boot gRPC server integration.
@GrpcService
public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBillingAccount(billing.BillingRequest billingRequest, StreamObserver<billing.BillingResponse> responseObserver) {
      // Log the incoming request for traceability.
      log.info("createBillingAccount request received: {}", billingRequest.toString());

      // TODO: implement business logic (persist account, validate patient, set status, etc.).

        // Build the response returned to the client.
        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId("2309825928") // Replace with actual generated ID
                .setStatus("ACTIVE")
                .build();

        // Complete the unary RPC with a single response message.
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
