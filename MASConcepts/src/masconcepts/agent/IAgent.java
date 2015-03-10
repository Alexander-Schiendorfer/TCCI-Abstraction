package masconcepts.agent;

import masconcepts.time.ITimeProvider;

/**
 * This interface should be implemented by a class that represents an agent in a common multi-agent system (MAS).<br />
 * The agent can be identified by a unique id, called agent identifier.
 * 
 * @author Gerrit
 * 
 */
public interface IAgent extends ITimeProvider {

	/**
	 * Returns the {@link IAgent}'s unique identifier.
	 * 
	 * @return
	 */
	public String getAgentIdentifier();

	/**
	 * This method should be periodically called by the underlying MAS and informs the agent to pursue its goals, i.e.,
	 * this method should trigger periodic actions performed by the agent.
	 */
	public void achieveGoals();

	/**
	 * Gets the {@link IAgent}'s {@link AgentModel}. Should be implemented on the {@link IAgent}'s specific type,
	 * containing relevant data representing the {@link IAgent}'s believe state.
	 * 
	 * @return the {@link IAgent}'s {@link AgentModel}
	 */
	public AgentModel getMyAgentModel();
}