<html>
    <head>
        <meta charset="utf-8">
        <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <table class="table table-bordered table-condensed">
                    <thead>
                    <tr>
                        <th>订单id</th>
                        <th>姓名</th>
                        <th>手机号</th>
                        <th>地址</th>
                        <th>金额</th>
                        <th>订单状态</th>
                        <th>支付状态</th>
                        <th>创建时间</th>
                        <th colspan="2">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#list list.content as dto>
                            <tr>
                                <td>${dto.orderId}</td>
                                <td>${dto.buyerName}</td>
                                <td>${dto.buyerPhone}</td>
                                <td>${dto.buyerAddress}</td>
                                <td>${dto.orderAmount}</td>
                                <td>${dto.getOrderStatusEnum().msg}</td>
                                <td>${dto.getPayStatusEnum().msg}</td>
                                <td>${dto.createTime}</td>
                                <td><a href="/seller/order/detail?orderId=${dto.orderId}">详情</a></td>
                                <td>
                                    <#if dto.getOrderStatusEnum().msg == "新订单">
                                        <a href="/seller/order/cancel?orderId=${dto.orderId}">取消</a>
                                    </#if>
                                </td>
                            </tr>
                        </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    </body>
</html>