package xyz.xnmq.inf.video.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import xyz.xnmq.entity.video.Bgm;

import java.util.List;

/**
 * @Author: by xnmq
 * @Data: 2019/7/31
 * @Description:
 */
public interface BgmDao extends JpaRepository<Bgm,Long>, JpaSpecificationExecutor<Bgm>{

    List<Bgm> findByDisabled(int disabled);
}
