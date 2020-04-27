package edu.gmu.cs321.team3.shiftmanager.users;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.gmu.cs321.team3.shiftmanager.orgs.Org;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);

    public Set<User> findAllByOrg(Org org);

    public Set<User> findAllByEmailIn(String[] emails);
}
