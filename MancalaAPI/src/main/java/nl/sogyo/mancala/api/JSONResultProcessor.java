/**
 * 
 */
package nl.sogyo.mancala.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import nl.sogyo.mancala.domain.Mancala;

/**
 * @author rvvugt
 *
 */
public class JSONResultProcessor {

	/**
	 * @param mancala
	 * @param player1
	 * @param player2
	 * @return
	 */
	public String createJSONResponse(Mancala mancala) {
		
		JSONObject result = new JSONObject();
		
		JSONObject jsonPlayer1 = this.createJSONPlayer(1, mancala, 1);
		JSONObject jsonPlayer2 = this.createJSONPlayer(8, mancala, 2);
        
		JSONArray players = new JSONArray();
        players.add(jsonPlayer1);
        players.add(jsonPlayer2);
        
        result.put("players", players);
        
        JSONObject gameStatus = new JSONObject();
        if (mancala.isEndOfGame()) {
        	gameStatus.put("endOfGame", true);
        	gameStatus.put("winner", mancala.getWinnersName());
        } else {
        	gameStatus.put("endOfGame", false);
        }
        result.put("gameStatus", gameStatus);
        
		return result.toJSONString();
	}
	
	/**
	 * @param startIndex
	 * @param player
	 * @param domainPlayer
	 * @param mancala
	 * @return
	 */
	private JSONObject createJSONPlayer(int startIndex, Mancala mancala, int playerIndex) {
		
		JSONObject jsonPlayer = new JSONObject();
		
		if (startIndex < 8) {
			jsonPlayer.put("type", "player1");
		} else {
			jsonPlayer.put("type", "player2");
		}
		
		jsonPlayer.put("name", mancala.getPlayerName(playerIndex));
		
		if (mancala.isToMovePlayer(playerIndex)) {
			jsonPlayer.put("hasTurn", true);
		} else {
			jsonPlayer.put("hasTurn", false);
		}
		
		JSONArray pits = new JSONArray();
		for (int i = startIndex; i <= startIndex + 6; i++) {
			pits.add(this.createJSONPit(i, mancala));
		}
		jsonPlayer.put("pits", pits);
		
		return jsonPlayer;
	}
	
	/**
	 * @param index
	 * @param mancala
	 * @return
	 */
	private JSONObject createJSONPit(int index, Mancala mancala) {
		
		JSONObject pit = new JSONObject();
		pit.put("index", index);
		pit.put("nr_of_stones", mancala.getStonesForPit(index));
		return pit;
	}
 
}
