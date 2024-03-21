/**
 * @author arashxr
 * @email ariuschamreun15@gmail.com
 * @github <a href="https://github.com/Arius-Chamreun">Github Url</a>
 */
package com.Arius.myNetwork.repo;

import com.Arius.myNetwork.entity.Contact;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ContactRepo extends JpaRepository<Contact,Long>, PagingAndSortingRepository<Contact,Long> {

    void deleteByNameAndId(String name, Long id);
    @NotNull Optional<Contact> findById(@NotNull Long id);

}
