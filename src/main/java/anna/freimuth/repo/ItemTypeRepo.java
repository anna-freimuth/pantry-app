package anna.freimuth.repo;

import anna.freimuth.entity.ItemType;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ItemTypeRepo extends CrudRepository<ItemType,Integer>{
   ItemType findFirstByTypeName(String typeName);

}
