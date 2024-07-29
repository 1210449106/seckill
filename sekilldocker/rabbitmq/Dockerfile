FROM rabbitmq:3.13.4-management
LABEL authors="Wang Ruiyang"

# 设置作者
LABEL maintainer="wangruiyang"

# 设置工作目录
WORKDIR /usr

# 复制插件到工作目录
COPY rabbitmq_delayed_message_exchange-3.13.0.ez /plugins
RUN rabbitmq-plugins enable rabbitmq_delayed_message_exchange

# 对外暴露端口
EXPOSE 5672
EXPOSE 15672


# 执行命令
CMD "rabbitmq-server"