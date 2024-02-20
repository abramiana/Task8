-- Вибірка працівників з найбільшою заробітною платою
select *
from worker
where SALARY = (select MAX(SALARY) from worker);