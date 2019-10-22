layui.use(['form', 'layer', 'laydate', 'table', 'laytpl'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;

    //违纪列表
    var tableIns = table.render({
        elem: '#newsList',
        url: '/getStuList',
        cellMinWidth: 95,
        page: false,
        height: "full-125",
        limit: 20,
        limits: [10, 15, 20, 25],
        id: "newsListTable",
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {field: 'sname', title: '姓名', align: "center"},
            {field: 'snum', title: '学号', align: 'center'},
            {field: 'sinstitute', title: '学院', align: 'center'},
            {field: 'sex', title: '性别', align: 'center'},
            {field: 'phone', title: '电话', align: 'center'},
            {field: 'idCard', title: '身份证号', align: 'center'},
            // {
            //     field: 'msgDate', title: '处分时间', align: 'center', templet: function (d) {
            //         return '<input type="checkbox" name="newsTop" lay-filter="newsTop" lay-skin="switch" lay-text="是|否" ' + d.newsTop + '>'
            //     }
            // },
            // {
            //     field: 'newsTime', title: '发布时间', align: 'center', minWidth: 110, templet: function (d) {
            //         return d.newsTime.substring(0, 10);
            //     }
            // },
            {title: '操作', width: 170, templet: '#newsListBar', fixed: "right", align: "center"}
        ]]
    });

    //是否置顶
    // form.on('switch(newsTop)', function(data){
    //     var index = layer.msg('修改中，请稍候',{icon: 16,time:false,shade:0.8});
    //     setTimeout(function(){
    //         layer.close(index);
    //         if(data.elem.checked){
    //             layer.msg("置顶成功！");
    //         }else{
    //             layer.msg("取消置顶成功！");
    //         }
    //     },500);
    // })

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click", function () {
        if ($(".searchVal").val() != '') {
            table.reload("newsListTable", {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: $(".searchVal").val()  //搜索的关键字
                }
            })
        } else {
            layer.msg("请输入搜索的内容");
        }
    });

    function addStu(edit) {
        var index = layui.layer.open({
            title: "修改学生信息",
            type: 2,
            content: "/stuAddPage",
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                if (edit) {
                    body.find(".sname").val(edit.sname);
                    body.find(".snum").val(edit.snum);
                    body.find(".sinstitute").val(edit.sinstitute);
                    body.find(".sex").val(edit.sex);
                    body.find(".phone").val(edit.phone);
                    body.find(".idCard").val(edit.idCard);
                    body.find(".flag").val('edit');
                    form.render();
                }
                setTimeout(function () {
                    layui.layer.tips('点击此处返回学生列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        })
        layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layui.layer.full(index);
        })
    }

    $(".addStu_btn").click(function () {
        addStu();
    })

    //批量删除
    $(".delAll_btn").click(function () {
        var checkStatus = table.checkStatus('newsListTable'),
            data = checkStatus.data,
            newsId = [];
        if (data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].newsId);
            }
            layer.confirm('确定删除选中信息？', {icon: 3, title: '提示信息'}, function (index) {
                console.log(data)

                // $.get("删除文章接口",{
                //     newsId : newsId  //将需要删除的newsId作为参数传入
                // },function(data){
                tableIns.reload();
                layer.close(index);
                // })
            })
        } else {
            layer.msg("请选择需要删除的信息");
        }
    })

    //列表操作
    table.on('tool(newsList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if (layEvent === 'edit') { //编辑
            addStu(data);
        } else if (layEvent === 'del') { //删除
            layer.confirm('确定删除此信息？', {icon: 3, title: '提示信息'}, function (index) {
                // $.get("删除文章接口",{
                //     newsId : data.newsId  //将需要删除的newsId作为参数传入
                // },function(data){

                console.log(data)
                $ = layui.jquery;
                $.ajax({
                    url: "/delStu?snum="+data.snum,
                    type: "get",
                    async: false,
                    dataType: "json",//返回的数据类型
                    //contentType: "application/json;charset=utf-8",//发送的数据类型
                    //data: JSON.stringify(postData),
                    success: function (data) {
                        console.log(data)

                    },
                    error: function (e) {

                    }
                })

                tableIns.reload();
                layer.close(index);
                // })
            });
        }
        // else if(layEvent === 'look'){ //预览
        //     layer.alert("此功能需要前台展示，实际开发中传入对应的必要参数进行文章内容页面访问")
        // }
    });

})