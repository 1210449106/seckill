-- 映射关系key
local mapperKey = KEYS[1]
-- 库存key
local countKey = KEYS[2]
-- 限流key
local limitKey = KEYS[3]
-- 用户id
local userId = ARGV[1]
-- 限流最大人数
local maxVisite = ARGV[2]

-- 获取当前访问人数
local visiteCount = tonumber(redis.call("get",limitKey) or "0")
-- 判断访问人数是否超标
if (visiteCount >= tonumber(maxVisite)) then
    -- 超标 更新数据后返回,拒绝处理
    return -503
end

-- 未超标,新建限流key或值+1
if (tonumber(redis.call("incr",limitKey)) == 1) then
    -- 设置有效时间
    redis.call("expire",limitKey,"2")
end

-- 判断用户是否买过
local isBuy = tonumber(redis.call("exists", mapperKey))
if(isBuy == 1) then
    return -501
end

-- 判断库存是否充足
-- 获取库存
local count = tonumber(redis.call("get", countKey))
if (count <= 0) then
    -- 库存不足
    return -502
end

-- 库存充足且首次下单
-- 写入映射,扣减库存
redis.call("set", mapperKey, userId)
redis.call("decr", countKey)
return 200