layui.use(['form', 'layer', 'layedit', 'laydate', 'upload'], function () {

    if (document.getElementById("flag").value=='edit'){
        document.getElementById("snum").disabled="disabled";
        document.getElementById("passDiv").style.visibility="hidden";
    }

    var form = layui.form
    layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;

    //用于同步编辑器内容到textarea
    layedit.sync(editIndex);

    //上传缩略图
    upload.render({
        elem: '.thumbBox',
        url: '../../json/userface.json',
        method: "get",  //此处是为了演示之用，实际使用中请将此删除，默认用post方式提交
        done: function (res, index, upload) {
            var num = parseInt(4 * Math.random());  //生成0-4的随机数，随机显示一个头像信息
            $('.thumbImg').attr('src', res.data[num].src);
            $('.thumbBox').css("background", "#fff");
        }
    });

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
    laydate.render({
        elem: '#release',
        type: 'datetime',
        trigger: "click",
        done: function (value, date, endDate) {
            submitTime = value;
        }
    });
    form.on("radio(release)", function (data) {
        if (data.elem.title == "定时发布") {
            $(".releaseDate").removeClass("layui-hide");
            $(".releaseDate #release").attr("lay-verify", "required");
        } else {
            $(".releaseDate").addClass("layui-hide");
            $(".releaseDate #release").removeAttr("lay-verify");
            submitTime = time.getFullYear() + '-' + (time.getMonth() + 1) + '-' + time.getDate() + ' ' + time.getHours() + ':' + time.getMinutes() + ':' + time.getSeconds();
        }
    });

    form.verify({
        sname: function (val) {
            console.log(val);
            if (val == '') {
                return "姓名不能为空";
            }
        },
        snum: function (val) {
            console.log(val);
            if (val == '') {
                return "学号不能为空";
            }
        },
        sinstitute: function (val) {
            console.log(val);
            if (val ==undefined) {
                return "学院不能为空";
            }
        },
        sex: function (val) {
            console.log(val);
            if (val == '') {
                return "性别不能为空";
            }
        },
        phone: function (val) {
            console.log(val);
            if (val == '') {
                return "电话不能为空";
            }
        },
        idCard: function (val) {
            console.log(val);
            if (val == '') {
                return "身份证号不能为空";
            }
        }

    })
    form.on("submit(addStu)", function (data) {
        if($(".sinstitute").val()==null){
            top.layer.close(index);
            top.layer.msg("请选择学院！")
            return;
        }
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        var institutes = ["教务处", "学生会", "院办公室", "医学院", "财会学院", "经济管理学院", "艺术设计学院", "外语学院", "旅游管理学院", "文学院", "信息学院", "宿舍管理科"];

        postData = {};
        postData["sname"] = $(".sname").val();
        postData["snum"] = $(".snum").val();
        postData["sinstitute"] = institutes[$(".sinstitute").val()];
        postData["phone"] = $(".phone").val();
        postData["idCard"] = $(".idCard").val();
        postData["sex"] = data.field.sex;;
        postData["password"] = $(".password").val();

        console.log(postData);

        // $.ajax({
        //     url: "/getUser",
        //     type: "get",
        //     async: false,
        //     dataType: "json",//返回的数据类型
        //     success: function (data) {
        //         console.log(data)
        //         postData["tnum"] = data[0];
        //     },
        //     error: function (e) {
        //     }
        // })

        _url = '/editStu'
        if (document.getElementById("flag").value == 'add') {
            _url = '/stuReg';
        }

        $.ajax({
            url: _url,
            type: "post",
            async: true,
            dataType: "json",//返回的数据类型
            contentType: "application/json;charset=utf-8",//发送的数据类型
            data: JSON.stringify(postData),
            success: function (data) {
                console.log("success")
                setTimeout(function () {
                    top.layer.close(index);
                    top.layer.msg("学生信息修改成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                }, 500);
            },
            error: function (e) {
                // console.log(e.status);
                top.layer.close(index);
                top.layer.msg("学生信息修改失败！")
                console.log("error")

            }
        })

    })


    //创建一个编辑器
    var editIndex = layedit.build('news_content', {
        height: 535,
        uploadImage: {
            url: "../../json/newsImg.json"
        }
    });

})