package org.nanocontainer.nanowar.chain.divertor;

import java.util.Map;

import org.nanocontainer.nanowar.chain.Divertor;
import org.picocontainer.PicoLifecycleException;

/**
 * divertor based on maps. uses FQCN as key, values shall be strings
 * 
 * @author k.pribluda
 * @version $Revision:$
 */
public class MapDivertor implements Divertor {

	Map diversions;

	public MapDivertor(Map diversions) {
		super();
		this.diversions = diversions;
	}
	/**
	 * recursively extract original cause from pico lifecycle exception
	 * 
	 */
	public String divert(Throwable cause) {
		if(cause instanceof PicoLifecycleException) {
			return divert(cause.getCause());
		}
		return (String) getDiversions().get(cause.getClass().getName());
	}

	public Map getDiversions() {
		return diversions;
	}

	public void setDiversions(Map diversions) {
		this.diversions = diversions;
	}

}
