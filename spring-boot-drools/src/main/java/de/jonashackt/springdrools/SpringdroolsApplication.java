package de.jonashackt.springdrools;

import java.math.BigDecimal;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.jonashackt.springdrools.internalmodel.ItemCity;
import de.jonashackt.springdrools.internalmodel.ItemCity.City;
import de.jonashackt.springdrools.internalmodel.ItemCity.Type;

@SpringBootApplication
public class SpringdroolsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringdroolsApplication.class, args);
	}

	@Autowired
	private KieSession ksession;

	@Override
	public void run(String... arg0) throws Exception {
		try {

			ItemCity item1 = new ItemCity();
			item1.setPurchaseCity(City.NEW_JERSEY);
			item1.setTypeofItem(Type.MEDICINES);
			item1.setSellPrice(new BigDecimal(10));
			ksession.insert(item1);

			ItemCity item2 = new ItemCity();
			item2.setPurchaseCity(City.NEW_JERSEY);
			item2.setTypeofItem(Type.GROCERIES);
			item2.setSellPrice(new BigDecimal(10));
			ksession.insert(item2);
			
			ksession.fireAllRules();
			System.out.println("Fired Drools rules for the first time.");
			
			ItemCity item3 = new ItemCity();
			item3.setPurchaseCity(City.NEW_ORLEANS);
			item3.setTypeofItem(Type.MEDICINES);
			item3.setSellPrice(new BigDecimal(10));
			ksession.insert(item3);

			ItemCity item4 = new ItemCity();
			item4.setPurchaseCity(City.NEW_ORLEANS);
			item4.setTypeofItem(Type.GROCERIES);
			item4.setSellPrice(new BigDecimal(10));
			ksession.insert(item4);
			
			ksession.fireAllRules();

/*			System.out.println(item1.getPurchaseCity().toString() + " " + item1.getLocalTax().intValue());

			System.out.println(item2.getPurchaseCity().toString() + " " + item2.getLocalTax().intValue());

			System.out.println(item3.getPurchaseCity().toString() + " " + item3.getLocalTax().intValue());

			System.out.println(item4.getPurchaseCity().toString() + " " + item4.getLocalTax().intValue());
*/		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
