/*******************************************************************************
 * Copyright (c) 2016, NEC Europe Ltd.
 * All rights reserved.
 * 
 * Authors:
 *          * NEC IoT Platform Team - iotplatform@neclab.eu
 *          * Flavio Cirillo - flavio.cirillo@neclab.eu
 *          * Tobias Jacobs - tobias.jacobs@neclab.eu
 *          * Gurkan Solmaz - gurkan.solmaz@neclab.eu
 *          * Salvatore Longo
 *          * Raihan Ul-Islam
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions 
 * are met:
 * 1. Redistributions of source code must retain the above copyright 
 * notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above 
 * copyright notice, this list of conditions and the following disclaimer 
 * in the documentation and/or other materials provided with the 
 * distribution.
 * 3. All advertising materials mentioning features or use of this 
 * software must display the following acknowledgment: This 
 * product includes software developed by NEC Europe Ltd.
 * 4. Neither the name of NEC nor the names of its contributors may 
 * be used to endorse or promote products derived from this 
 * software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY NEC ''AS IS'' AND ANY 
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT 
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY 
 * AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN 
 * NO EVENT SHALL NEC BE LIABLE FOR ANY DIRECT, INDIRECT, 
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL 
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED 
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, 
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR 
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS 
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH 
 * DAMAGE.
 ******************************************************************************/

package eu.neclab.iotplatform.iotbroker.commons.interfaces;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Multimap;

import eu.neclab.iotplatform.ngsi.api.datamodel.ContextElement;
import eu.neclab.iotplatform.ngsi.api.datamodel.EntityId;

public interface EmbeddedAgentIndexerInterface {

	public abstract boolean index(
			ContextElement isolatedContextElement);

	/**
	 * This method will calculate a multimap containing all the entities with
	 * its attributes that are matching the EntityId criteria given as input. It
	 * supports pattern and filtering against EntityId type
	 * 
	 * @param entityId
	 * @return
	 */
	public abstract Multimap<String, String> matchingIdsAndAttributeNames(
			List<EntityId> entityIdList, Set<String> attributeNames);

	public abstract String generateKeyForHistoricalData(String entityId,
			String type, String attributeName, Date timestamp);

	/**
	 * IMPORTANT: id needs to be the one generated by generateId(String
	 * entityId, String type)
	 * 
	 * @param id
	 *            The full Id composed by generateId(String entityId, String
	 *            type)
	 * @param attributeName
	 * @param timestamp
	 * @return
	 */
	public abstract String generateKeyForHistoricalData(String id,
			String attributeName, Date timestamp);

	public abstract String generateKeyForLatestValue(String entityId,
			String type, String attributeName);

	public abstract String generateKeyForLatestValue(
			ContextElement isolatedContextElement);

	/**
	 * IMPORTANT: id needs to be the one generated by generateId(String
	 * entityId, String type)
	 * 
	 * @param id
	 *            The full Id composed by generateId(String entityId, String
	 *            type)
	 * @param attributeName
	 * @return
	 */
	public abstract String generateKeyForLatestValue(String id,
			String attributeName);

	/**
	 * It generated the id of the contextElement by concatenating entityId.id
	 * and entityId.type separate by the separator specified in the CouchDBUtil.
	 * If entityId.type is null or empty, the id will be just the entityId.id
	 * 
	 * @param entityId
	 * @param type
	 * @return the identifier
	 */
	public abstract String generateId(String entityId, String type);

	public abstract String generateId(EntityId entityId);

	/**
	 * Return an array of String where the first element is the entityId.id and
	 * the second element is the entity.type if present. If entity.type is not
	 * present the array will be composed by only one String, the entity.id
	 * 
	 * @param id
	 * @return
	 */
	public abstract String[] splitEntityAndType(String id);

	public abstract EntityId getEntityId(String id);

}