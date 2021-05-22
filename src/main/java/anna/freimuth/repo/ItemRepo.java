package anna.freimuth.repo;

import anna.freimuth.entity.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepo extends CrudRepository<Item, Long>{

}
