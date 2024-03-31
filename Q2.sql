# Display all the orders along with product name ordered by a customer having Customer_Id=2

select c.CUS_ID,c.CUS_NAME, p.ord_id,p.ord_amount from
(
   SELECT O.* FROM `ORDER` AS O
   inner join supplier_pricing as sp
   where o.PRICING_ID=sp.PRICING_ID
) as p
inner join customer as c 
where p.CUS_ID=c.CUS_ID and c.CUS_ID=2;