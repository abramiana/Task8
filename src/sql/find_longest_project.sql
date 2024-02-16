SELECT *,
       DATEDIFF(FINISH_DATE, START_DATE) / 30 AS DurationInMonths
FROM project
ORDER BY DurationInMonths DESC
LIMIT 1;
