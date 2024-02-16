

-- Вивести список проєктів та їх вартість (відсортовані за спаданням)
select
	c.NAME as Name,
    coalesce(sum(w.SALARY * timestampdiff(month, p.START_DATE, p.FINISH_DATE)), 0) as Price
from
    project p
left join
    project_worker pw on p.ID = pw.PROJECT_ID
left join
    worker w on pw.WORKER_ID = w.ID
left join
	client c on p.CLIENT_ID = c.ID
group by
    p.ID, p.CLIENT_ID
having
    Price > 0
order by
    Price DESC;