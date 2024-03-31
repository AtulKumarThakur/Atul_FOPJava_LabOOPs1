# Create a stored procedure to display supplier id, name, Rating(Average rating of all the products sold by every customer) and
# Type_of_Service. For Type_of_Service, If rating =5, print “Excellent Service”,If rating >4 print “Good Service”, If rating >2 print “Average
# Service” else print “Poor Service”. Note that there should be one rating per supplier

select report.SUPP_ID, report.SUPP_NAME, report.average,
CASE
   WHEN report.Average = 5 THEN 'Excellent Service'
   WHEN report.Average > 4 THEN 'Good Service'
   WHEN report.Average > 2 THEN 'Average Service'
   ELSE 'Poor Service'
END AS Type_of_Service
from
(
   select final.supp_id, supplier.supp_name, final.average from
   (
      select test2.supp_id, sum(test2.rat_ratstars)/count(test2.rat_ratstars) as Average from
      (
         select supplier_pricing.supp_id, test.ORD_ID, test.RAT_RATSTARS from supplier_pricing
         inner join
         (
            select `order`.pricing_id, rating.ORD_ID, rating.RAT_RATSTARS from `order`
            inner join
            rating on rating.`ord_id` = `order`.ord_id
         ) as test on test.pricing_id = supplier_pricing.pricing_id
      ) as test2 group by supplier_pricing.supp_id
   )as final inner join supplier where final.supp_id = supplier.supp_id
) as report