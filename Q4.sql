#Find the least expensive product from each category and print the table with category id, name, product name and price of the product.

select c.CAT_ID,c.CAT_NAME, min(t3.min_price) as Min_price from category as c
inner join
(
   select p.CAT_ID, p.PRO_NAME, t2.* from product as p
   inner join
   (
     select pro_id, min(supp_price) as Min_price from supplier_pricing group by pro_id
   ) as t2 on t2.PRO_ID=p.PRO_ID
) as t3 on t3.CAT_ID=c.CAT_ID group by t3.CAT_ID;