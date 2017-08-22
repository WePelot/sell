<html>
<#include "../common/header.ftl">
<body>
    <div id="wrapper" class="toggled">
        <#--边栏sidebar-->
        <#include "../common/nav.ftl">
        <#--主要内容content-->
        <div id="page-content-wrapper">
            <div class="container-fluid">
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
                    <div class="col-md-12 column">
                        <ul class="pagination pull-right">
                        <#if currentPage lte 1>
                            <li class="disabled"><a href="#">上一页</a></li>
                        <#else >
                            <li><a href="/seller/order/list?page=${currentPage -1}&size=${size}">上一页</a></li>
                        </#if>
                        <#list 1..list.getTotalPages() as index>
                            <#if currentPage == index>
                                <li class="disabled"><a href="#">${index}</a></li>
                            <#else >
                                <li class=><a href="/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                            </#if>
                        </#list>
                        <#if currentPage gte list.getTotalPages()>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else >
                            <li><a href="/seller/order/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                        </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        var websocket = null;
        if('WebSocket' in window){
          //判断浏览器是否支持webSocket
          websocket = new WebSocket('ws://sellweb.natapp1.cc/websocket');
//          websocket = new WebSocket('ws://localhost:8099/websocket');
        }else{
          alert("该浏览器不支持WebSocket");
        }

        websocket.onopen = function (event) {
            console.log("建立连接");
        };

        websocket.onclose = function (event) {
             console.log("连接关闭");
        };

        websocket.onmessage = function (event) {
            console.log('收到消息：' + event.data);
            //弹窗提醒，播放音乐
        };

        websocket.onerror = function () {
            alert("WebSocket通信发送错误!");
        };

        window.onbeforeunload = function () {
            websocket.close();
        }
    </script>
</body>
</html>