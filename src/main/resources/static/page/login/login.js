layui.config({
    base: "js/"
}).use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;
    //video背景
    $(window).resize(function () {
        if ($(".video-player").width() > $(window).width()) {
            $(".video-player").css({
                "height": $(window).height(),
                "width": "auto",
                "left": -($(".video-player").width() - $(window).width()) / 2
            });
        } else {
            $(".video-player").css({
                "width": $(window).width(),
                "height": "auto",
                "left": -($(".video-player").width() - $(window).width()) / 2
            });
        }
    }).resize();

    //登录按钮事件1
    form.on("submit(login1)", function (data) {
        //window.location.href = "/index";
        window.event.returnValue = false
        userName = document.getElementById("userName").value;
        password = document.getElementById("password").value;

        $.ajax({
            url: "/check?userName=" + userName + "&password=" + password + "&role=stu",
            type: "get",
            async: true,
            dataType: "json",//返回的数据类型
            success: function (data) {
                window.location.href = "/index";
            },
            error: function (e) {
                // console.log(e.status);
                // console.log(e.responseText);
                var layer = layui.layer;
                layer.msg('登陆失败');
            }
        })

        return false;
    })

    //登录按钮事件2
    form.on("submit(login2)", function (data) {
            //window.location.href = "/index";
        window.event.returnValue = false
            userName = document.getElementById("userName").value;
            password = document.getElementById("password").value;

            $.ajax({
                url: "/check?userName=" + userName + "&password=" + password + "&role=tea",
                type: "get",
                async: true,
                dataType: "json",//返回的数据类型
                success: function (data) {
                    window.location.href = "/index";
                },
                error: function (e) {
                    // console.log(e.status);
                    // console.log(e.responseText);
                    var layer = layui.layer;
                    layer.msg('登陆失败');
                }
            })

            return false;
        })

    //登录按钮事件3
    form.on("submit(login3)", function (data) {
            //window.location.href = "/index";
            userName = document.getElementById("userName").value;
            password = document.getElementById("password").value;
            window.event.returnValue = false

            $.ajax({
                url: "/check?userName=" + userName + "&password=" + password + "&role=adm",
                type: "get",
                async: true,
                dataType: "json",//返回的数据类型
                success: function (data) {
                    window.location.href = "/index";
                },
                error: function (e) {
                    // console.log(e.status);
                    // console.log(e.responseText);
                    var layer = layui.layer;
                    layer.msg('登陆失败');
                }
            })

            return false;
        }
    )

    //登录按钮事件4
    form.on("submit(login4)", function (data) {
            //window.location.href = "/index";
            userName = document.getElementById("userName").value;
            password = document.getElementById("password").value;
            window.event.returnValue = false

            $.ajax({
                url: "/check?userName=" + userName + "&password=" + password + "&role=stuM",
                type: "get",
                async: true,
                dataType: "json",//返回的数据类型
                success: function (data) {
                    window.location.href = "/index";
                },
                error: function (e) {
                    // console.log(e.status);
                    // console.log(e.responseText);
                    var layer = layui.layer;
                    layer.msg('登陆失败');
                }
            })

            return false;
        }
    )

})
