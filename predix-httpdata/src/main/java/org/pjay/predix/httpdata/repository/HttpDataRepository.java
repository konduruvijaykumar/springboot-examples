/**
 * 
 */
package org.pjay.predix.httpdata.repository;

import java.util.List;

import org.pjay.predix.httpdata.entity.HttpData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author vkonduru3
 *
 */
public interface HttpDataRepository extends JpaRepository<HttpData, Long>{
	
	List<HttpData> findByTransferId(String transferId);
	
}
