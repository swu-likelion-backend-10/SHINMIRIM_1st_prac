package com.like.likelion1.repository;

import com.like.likelion1.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByNameContaining(String name);
}
