package oldmana.md.millionaire;

import oldmana.md.server.ChatColor;
import oldmana.md.server.card.Card;
import oldmana.md.server.card.collection.Bank;
import oldmana.md.server.event.EventHandler;
import oldmana.md.server.event.EventListener;
import oldmana.md.server.event.EventPriority;
import oldmana.md.server.event.state.RentPaymentEvent;

import java.util.List;

public class RentPaymentListener implements EventListener
{
	private Millionaire mod;
	
	public RentPaymentListener(Millionaire mod)
	{
		this.mod = mod;
	}
	
	@EventHandler (priority = EventPriority.LOW) // Listening on the LOW priority so other mods can know the event may be cancelled
	public void onRentPayment(RentPaymentEvent event)
	{
		// Check if the rule to pay money before properties is enabled
		if (!mod.getModRule().getSubrule("payMoneyFirst").getBoolean())
		{
			return;
		}
		
		List<Card> payment = event.getPayment();
		// Verify that the player is paying with their cash first
		if (payment.stream().anyMatch(card -> !(card.getOwningCollection() instanceof Bank)) &&
				!payment.containsAll(event.getTarget().getBank().getCards()))
		{
			// Cancel the event so their illegal payment doesn't go through
			event.setCancelled(true);
			event.getTarget().sendMessage(ChatColor.PREFIX_ALERT + "Millionaire edition rules require you to pay " +
					"with money before properties!");
		}
	}
}
