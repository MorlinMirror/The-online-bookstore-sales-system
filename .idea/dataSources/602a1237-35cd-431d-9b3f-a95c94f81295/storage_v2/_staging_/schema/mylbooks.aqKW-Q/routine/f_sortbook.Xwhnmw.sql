create procedure f_sortbook(IN id int)
BEGIN
	#Routine body goes here...
SELECT *
FROM
	t_book_sort JOIN t_book
on
			t_book.sort_id = t_book_sort.sort_id
		
		AND t_book.sort_id = id;
	
END;


