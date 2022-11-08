package uk.co.therhys.docuserve.Db;

import org.springframework.data.repository.CrudRepository;
import uk.co.therhys.docuserve.Db.Document;

public interface DocumentRepository extends CrudRepository<Document, Integer> {

}
