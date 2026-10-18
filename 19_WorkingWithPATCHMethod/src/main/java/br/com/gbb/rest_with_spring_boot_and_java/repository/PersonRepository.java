package br.com.gbb.rest_with_spring_boot_and_java.repository;

import br.com.gbb.rest_with_spring_boot_and_java.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Modifying(clearAutomatically = true) //para atualizar o cache o hibernate de forma automática.
    @Query("UPDATE Person p SET p.enabled = false WHERE p.id = :id")
    void disablePerson(@Param("id") Long id);

    @Modifying(clearAutomatically = true) //para atualizar o cache o hibernate de forma automática.
    @Query("UPDATE Person p SET p.enabled = true WHERE p.id = :id")
    void enablePerson(@Param("id") Long id);
}
