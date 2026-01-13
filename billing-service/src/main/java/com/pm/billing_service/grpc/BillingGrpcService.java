package com.pm.billing_service.grpc;

import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService // Marks this class as a gRPC service
public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBillingAccount(billing.BillingRequest billingRequest, StreamObserver<billing.BillingResponse> responseObserver) {
      log.info("createBillingAccount request received: {}", billingRequest.toString());

      //Business logic, save to DB, perform calculations, etc.

        // Create a billing response object
        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId("2309825928") // Replace with actual generated ID
                .setStatus("ACTIVE")
                .build();

        // Send the response back to the client
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
