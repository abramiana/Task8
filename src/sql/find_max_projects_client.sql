SELECT c.*, COUNT(p.ID) AS ProjectCount
FROM client c
JOIN project p ON c.ID = p.CLIENT_ID
GROUP BY c.ID
HAVING COUNT(p.ID) = (
    SELECT MAX(ProjectCount)
    FROM (
        SELECT COUNT(ID) AS ProjectCount
        FROM project
        GROUP BY CLIENT_ID
    ) AS ProjectCounts
);