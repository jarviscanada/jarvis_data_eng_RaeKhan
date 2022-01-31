-- round5 FUNCTION, ROUNDS TIMESTAMP TO NEAREST 5 MIN
CREATE FUNCTION round5(ts timestamp) RETURNS timestamp AS
$$
BEGIN
    RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
END;
$$
    LANGUAGE PLPGSQL;

-- 1. GROUP HOSTS BY HARDWARE INFO
SELECT DISTINCT cpu_number, id AS host_id, total_mem
FROM host_info
-- FULL JOIN host_usage ON host_id = id -- Using full join creates nulls
ORDER BY cpu_number, total_mem DESC ;

-- 2. AVG MEMORY USAGE
SELECT host_usage.host_id,
       host_info.hostname,
       round5(host_usage.timestamp) AS timestamp,
       AVG (((host_info.total_mem - host_usage.memory_free)/host_info.total_mem) * 100)
FROM host_info, host_usage
GROUP BY host_usage.timestamp, host_usage.host_id, host_info.hostname
ORDER BY host_usage.host_id;

-- 3. DETECT HOST FAILURE
SELECT host_usage.host_id,
       round5(host_usage.timestamp) AS timestamp,
       COUNT(host_usage.host_id) AS num_data_points
FROM host_usage
GROUP BY host_usage.host_id, timestamp
HAVING COUNT(host_usage.host_id) < 3
ORDER BY host_id;