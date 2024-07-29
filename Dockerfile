FROM openjdk:17
LABEL authors="Wang Ruiyang"

# 设置作者
LABEL maintainer="wangruiyang"

# 设置工作目录
WORKDIR /usr

# 复制jar包到工作目录
COPY seckill-web.jar /usr

# 对外暴露端口
EXPOSE 8080

# 执行命令
CMD ["java","-jar","/usr/seckill-web.jar"]