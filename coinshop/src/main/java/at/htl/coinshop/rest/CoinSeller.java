package at.htl.coinshop.rest;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/coinseller")
public class CoinSeller {

    @Inject
    @RestClient
    CoinResource coinResource;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Retry(maxRetries = 2)
    @Counted(name = "requests", description = "Number of requests")
    @Timed(name = "requestTime", description = "How long a request takes", unit = MetricUnits.MILLISECONDS)
    @Fallback(fallbackMethod = "getCoins_fallback")
    public Response getCoins() {
        return Response.ok().entity(coinResource.getCoins()).build();
    }

    public Response getCoins_fallback() {
        return Response.status(404).build();
    }

}