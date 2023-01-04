package tn.ipsas.produitservice;

import tn.ipsas.coremodels.events.ClientAddedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import tn.ipsas.coremodels.models.client.Client;

@Component
public class ClientAddedListener implements ApplicationListener<ClientAddedEvent> {


    @Override
    public void onApplicationEvent(ClientAddedEvent event) {
        Client client = (Client) event.getSource();

        System.out.println(client);
    }
}
