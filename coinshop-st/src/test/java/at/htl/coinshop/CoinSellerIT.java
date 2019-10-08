package at.htl.coinshop;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.json.JsonArray;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class CoinSellerIT {

    private static Client client;
    private static WebTarget tut;

    @BeforeAll
    public static void init(){
        client = ClientBuilder.newClient();
        tut = client.target("http://localhost:8181/coinseller/");
    }

    @Test
    public void crud(){
        Response response = this.tut.request().get() ;
        int status = response.getStatus();
        assertThat(status, is(200));
        JsonArray jsonArray  = response.readEntity(JsonArray.class);
        System.out.println("Coin list = " +  jsonArray);
    }

}
