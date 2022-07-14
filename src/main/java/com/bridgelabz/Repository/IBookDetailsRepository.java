package com.bridgelabz.Repository;

import com.bridgelabz.Model.BookDetails;
import com.bridgelabz.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBookDetailsRepository extends JpaRepository<BookDetails,Integer> {
 //@Query(value = "SELECT * FROM book_details inner join book_name on
    // book_details.book_id=book_name.book_id ",nativeQuery=true)


   @Query(value = "SELECT * FROM book_details WHERE book_name Like 'bookName' ",nativeQuery = true)
    List<BookDetails> findBookByName(String bookName);
}
