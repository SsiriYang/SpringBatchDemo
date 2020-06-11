package name.ealen.Dao;

import name.ealen.model.OutPutDay;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author 41765
 * @Creater 2020/6/10 15:50
 * Description
 */
public interface DaySummaryRepository extends CrudRepository<OutPutDay,String> {
}
