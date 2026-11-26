package kr.ac.shingu.soft.appfrm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatUserRepository extends CrudRepository<ChatUser, String> {
    public List<ChatUser> findAllChatUser();
}