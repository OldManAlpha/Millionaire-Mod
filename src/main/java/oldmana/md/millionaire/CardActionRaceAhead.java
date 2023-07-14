package oldmana.md.millionaire;

import oldmana.md.common.card.CardAnimationType;
import oldmana.md.server.Player;
import oldmana.md.server.card.CardAction;
import oldmana.md.server.card.CardTemplate;
import oldmana.md.server.card.CardType;
import oldmana.md.server.card.play.PlayArguments;

import static oldmana.md.server.card.CardAttributes.*;

public class CardActionRaceAhead extends CardAction
{
	@Override
	public void doPlay(Player player, PlayArguments args)
	{
		// Setting it as the player's turn again effectively restarts their turn
		getServer().getGameState().setTurn(player, true);
	}
	
	@Override
	public boolean canPlay(Player player)
	{
		// We want this card to only be able to played if the player has exactly 1 move left
		return getServer().getGameState().getMovesRemaining() == 1;
	}
	
	private static CardType<CardActionRaceAhead> createType()
	{
		CardType<CardActionRaceAhead> type = new CardType<CardActionRaceAhead>(
				CardActionRaceAhead.class, // Need to provide this class object
				CardActionRaceAhead::new, // The factory that creates new instances of this class
				"Race Ahead"); // The name of the card
		// Optionally, you can specify aliases after the name of the card
		
		CardTemplate template = type.getDefaultTemplate(); // Get a copy of the default template, which by default, is
		                                                   // derived from the parent's default template
		template.put(VALUE, 4);
		template.put(NAME, "Race Ahead");
		template.putStrings(DISPLAY_NAME, "RACE", "AHEAD"); // The lines shown on the card graphics
		template.put(FONT_SIZE, 8); // The font size of the text
		template.put(DISPLAY_OFFSET_Y, 1); // The y-offset of the text
		template.putStrings(DESCRIPTION, "Use on your last move to race ahead to your next turn.");
		template.put(UNDOABLE, false); // This card definitely should not be undoable
		template.put(CLEARS_UNDOABLE_ACTIONS, true); // You definitely should not be able to undo previously played cards
		template.put(PLAY_ANIMATION, CardAnimationType.IMPORTANT); // This plays as a special animation when played
		template.put(MOVE_COST, 0); // Costs no moves to play
		
		type.setDefaultTemplate(template); // Sets the default template to the template we just wrote to
		
		// The Millionaire deck will use this template since action cards have no value in Millionaire Edition
		CardTemplate valueless = template.clone();
		valueless.put(VALUE, 0);
		valueless.put(INNER_COLOR, "$4"); // Setting the color to what 4M would be
		type.addTemplate(valueless, "Valueless"); // Add the template with the name "Valueless"
		return type;
	}
}
