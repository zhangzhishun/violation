layui.use(['form', 'layer'], function () {

    var form = layui.form
    layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    form.on("submit(addUser)", function (data) {
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        // 实际使用时的提交信息
        // $.post("上传路径",{
        //     userName : $(".userName").val(),  //登录名
        //     userEmail : $(".userEmail").val(),  //邮箱
        //     userSex : data.field.sex,  //性别
        //     userGrade : data.field.userGrade,  //会员等级
        //     userStatus : data.field.userStatus,    //用户状态
        //     newsTime : submitTime,    //添加时间
        //     userDesc : $(".userDesc").text(),    //用户简介
        // },function(res){
        //
        // })

        var institutes = ["教务处", "学生会", "院办公室", "医学院", "财会学院", "经济管理学院", "艺术设计学院", "外语学院", "旅游管理学院", "文学院", "信息学院", "宿舍管理科"];

        postData = {};
        postData["sname"] = $(".sname").val();
        postData["snum"] = $(".snum").val();
        postData["sex"] = data.field.sex;
        postData["sinstitute"] = institutes[data.field.sinstitute];
        postData["phone"] = $(".phone").val();
        postData["idCard"] = $(".idCard").val();
        postData["password"] = $(".password").val();

        console.log(postData)

        flag = false;

        $.ajax({
            url: "/stuReg",
            type: "post",
            async: false,
            dataType: "json",//返回的数据类型
            contentType: "application/json;charset=utf-8",//发送的数据类型
            data: JSON.stringify(postData),
            success: function (data) {
                // layer.msg('注册成功');
                flag = true;
                // window.location.href = "/login";
                console.log("success")
                //layer.msg('注册成功');
            },
            error: function (e) {
                // console.log(e.status);
                // console.log(e.responseText);
                console.log("error")

            }
        })

        var layer = layui.layer;
        if (flag) {
            layer.msg('注册成功，自动跳转');
            window.event.returnValue = false
            setTimeout(function () {
                window.location.href = "/login";
            }, 1000)
        } else {
            layer.msg('注册失败');
        }


    })

    //格式化时间
    function filterTime(val) {
        if (val < 10) {
            return "0" + val;
        } else {
            return val;
        }
    }

    //定时发布
    var time = new Date();
    var submitTime = time.getFullYear() + '-' + filterTime(time.getMonth() + 1) + '-' + filterTime(time.getDate()) + ' ' + filterTime(time.getHours()) + ':' + filterTime(time.getMinutes()) + ':' + filterTime(time.getSeconds());

})