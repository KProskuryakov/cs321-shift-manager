package edu.gmu.cs321.team3.shiftmanager.orgs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgRepository extends JpaRepository<Org, Long> {

	public Org findByName(String name;)

}