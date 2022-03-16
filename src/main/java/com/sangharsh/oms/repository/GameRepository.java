package com.sangharsh.oms.repository;

import com.sangharsh.oms.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends BaseRepository<Game> {


}
