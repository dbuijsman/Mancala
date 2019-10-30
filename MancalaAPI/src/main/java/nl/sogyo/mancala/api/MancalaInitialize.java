/**
 * 
 */
package nl.sogyo.mancala.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import nl.sogyo.mancala.domain.Mancala;
import nl.sogyo.mancala.domain.MancalaImpl;
import nl.sogyo.mancala.domain.MancalaImplemented;

/**
 * @author rvvugt
 *
 */
@Path("players")
public class MancalaInitialize {

	/**
	 * @param request
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response initialize(
			@Context HttpServletRequest request, 
			PlayerData players) {
		
		HttpSession session= request.getSession(true);
		Mancala mancala = new MancalaImplemented();
		
		String namePlayer1 = players.nameplayer1;
		String namePlayer2 = players.nameplayer2;
		
		mancala.setPlayerName(namePlayer1, 1);
		mancala.setPlayerName(namePlayer2, 2);
		
		session.setAttribute("mancala", mancala);		
		
		String output = new JSONResultProcessor().createJSONResponse(mancala);
		
		return Response.status(200).entity(output).build();
	}
	
}
