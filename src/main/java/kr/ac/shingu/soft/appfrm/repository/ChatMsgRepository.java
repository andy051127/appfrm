package kr.ac.shingu.soft.appfrm.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMsgRepository extends CrudRepository<ChatMsg, Long> {
    public Iterable<ChatMsg> findAll(Sort sort);
}
