layui.use(['form', 'layer', 'layedit', 'laydate', 'upload'], function () {

    if (document.getElementById("flag").value=='edit'){
        document.getElementById("msgNum"). disabled="disabled";
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
        msgType: function (val) {
            console.log(val);
            if (val == '') {
                return "处分类型不能为空";
            }
        },
        msgRemarks: function (val) {
            console.log(val);
            if (val == '') {
                return "备注不能为空";
            }
        },
        msgNum: function (val) {
            console.log(val);
            if (val == '') {
                return "处分编号不能为空";
            }
        },
        msgDate: function (val) {
            console.log(val);
            if (val == '') {
                return "处分时间不能为空";
            }
        },
        msgSnum: function (val) {
            console.log(val);
            if (val == '') {
                return "学生学号不能为空";
            }
        }

    })
    form.on("submit(addNews)", function (data) {
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});

        postData = {};
        postData["msgType"] = $(".msgType").val();
        postData["msgRemarks"] = $(".msgRemarks").val();
        postData["msgNum"] = $(".msgNum").val();
        postData["msgDate"] = $(".msgDate").val();
        postData["snum"] = $(".msgSnum").val();
        console.log(postData);

        $.ajax({
            url: "/getUser",
            type: "get",
            async: false,
            dataType: "json",//返回的数据类型
            success: function (data) {
                console.log(data)
                postData["tnum"] = data[0];
            },
            error: function (e) {
            }
        })

        _url = '/vioEdit'
        if (document.getElementById("flag").value == 'add') {
            _url = '/vioAdd';
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
                    top.layer.msg("违纪信息修改成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                }, 500);
            },
            error: function (e) {
                // console.log(e.status);
                top.layer.close(index);
                top.layer.msg("违纪信息修改失败！")
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