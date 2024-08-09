package com.beibei.house_search.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beibei.house_search.domain.pojo.Location;
import com.beibei.house_search.mapper.LocationMapper;
import com.beibei.house_search.service.LocationService;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl extends ServiceImpl<LocationMapper, Location> implements LocationService {
}
