package oldmana.md.millionaire;

import oldmana.md.server.card.CardRegistry;
import oldmana.md.server.card.collection.deck.CustomDeck;
import oldmana.md.server.mod.ServerMod;
import oldmana.md.server.rules.struct.RuleStructKey.RuleKeyBuilder;

public class Millionaire extends ServerMod
{
	@Override
	public void onLoad()
	{
		// It is important that rules are created in onLoad() rather than onEnable()
		
		// Create the rule that determines if money must be paid before properties
		RuleKeyBuilder.from(getModRuleStruct())
				.jsonName("payMoneyFirst")
				.name("Pay Money Before Properties")
				.description("If enabled, rent charges must be paid with money before properties.")
				.defaultValue(false)
				.reducible(true)
				.register();
	}
	
	@Override
	public void onEnable()
	{
		// Register the Millionaire property colors
		MillionairePropertyColors.initializeColors();
		
		// Register the Race Ahead action card
		CardRegistry.registerCardType(this, CardActionRaceAhead.class);
		
		// Load the deck that's in the jar
		CustomDeck deck = new CustomDeck("millionaire", Millionaire.class.getResourceAsStream("/millionairedeck.json"));
		
		// Register the deck for use by the server
		getServer().getDeck().registerDeckStack("millionaire", deck);
		
		// Register the listener that ensures players pay with their money first
		getServer().getEventManager().registerEvents(new RentPaymentListener(this));
	}
}
