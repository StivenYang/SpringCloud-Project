package top.hengshare.cloud.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.hengshare.cloud.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
