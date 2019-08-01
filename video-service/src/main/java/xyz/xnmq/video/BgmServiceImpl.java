package xyz.xnmq.video;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.xnmq.entity.video.Bgm;
import xyz.xnmq.inf.video.BgmService;
import xyz.xnmq.inf.video.dao.BgmDao;
import xyz.xnmq.inf.video.dto.BgmDto;
import xyz.xnmq.inf.video.mapper.BgmMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: by xnmq
 * @Data: 2019/7/31
 * @Description:
 */
@Service
public class BgmServiceImpl implements BgmService{
    @Autowired
    private BgmMapper bgmMapper;
    @Autowired
    private BgmDao bgmDao;


    @Override
    public List<BgmDto> findList(){
        List<Bgm> bgmList = bgmDao.findByDisabled(0);
        List<BgmDto> dtoList = new ArrayList<>();
        for(Bgm bgm : bgmList){
            BgmDto dto = new BgmDto();
            BeanUtils.copyProperties(bgm, dto);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
