package com.indusnet.repository;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.indusnet.model.UserModel;
@Repository
public interface IUserModelRepository extends JpaRepository<UserModel, BigInteger> {}
