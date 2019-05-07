/**
 * Created by 耿飞 on 2016/3/8.
 */

//var defaultUrl = "http://jsjwhj.oicp.net/active/";
var defaultUrl = "http://localhost:8080/active/";


function nav() {
    if (localStorage.getItem("sno") == null && localStorage.getItem("tno") == null) {
        //$(".top_bar_right").append("<div id=\"user\" style=\"border-radius: 3px;\">立即登录</div>");
        location.href = "login.html";
        return;
    } else {
        topBar();
        navigation();
        nullAjax();
        loading();
        toTop();
        search();
    }
}
function topBar() {
    $(".top_bar_right").append("<div id=\"user\"> 当前用户：" + localStorage.getItem("sname") + "</div><div id=\"exit\"> 退出</div>");
    $("#exit").click(function () {
        if (confirm("确定要退出吗？")) {
            localStorage.removeItem("sno");
            localStorage.removeItem("sname");
            localStorage.removeItem("spwd");
            location.href = "login.html";
        }
    });
}
function navigation() {
    var navList = [
        {name: "录像教学", href: "video.html", child: []},
        {name: "课前预习", href: "preview.html",child:[]},
        {name: "自学材料", href: "document.html", child: []},
        {name: "算法动画", href: "flash.html", child: []},
        {name: "练习模块", href: "training.html", child: []},
        {name:"测试模块",href:"test.html",child:[]},
        {name: "课后反馈", href: "classfeedback.html", child: []},
        {
            name: "其他模块", href: "#",
            child: [
                {name: "个人信息", href: "info.html", child: []},
                {name: "密码修改", href: "pwd.html", child: []}
            ]
        }
    ]
    for (var i = 0; i < navList.length; i++) {
        var id = "navigation-li" + i;
        var cla = "";
        if ($("#father-page").text() == navList[i].href) {
            cla = "sel";
        }
        $(".nav ul").append("<li id='" + id + "'><a class='" + cla + "' href='" + navList[i].href + "'>" + navList[i].name + "</a></li>");
        if (navList[i].child.length > 0) {
            $("#" + id).append("<div class='sub-child'></div>");
            for (var j = 0; j < navList[i].child.length; j++) {
                $("#" + id + " .sub-child").append("<a href='" + navList[i].child[j].href + "'>" + navList[i].child[j].name + "</a>");
            }
        }
    }
    $('.nav li').hover(function () {
        $(this).children('a').toggleClass('sel');
        $(this).children('.sub-child').slideDown(300).filter(':not(:animated)');
    }, function () {
        $(this).children('a').toggleClass('sel');
        $(this).children('.sub-child').stop().slideUp(100);
    });
}
function toTop() {
    $("body").append("<div id=\"to-top\"><i class=\"fa fa-arrow-circle-up\"></i></div>");
    var offsetTop = 342;
    $(window).scroll(function () {
        if ($(window).scrollTop() > 0) {
            $("#to-top").slideDown(300);
        } else {
            $("#to-top").slideUp(300);
        }
        if ($(window).scrollTop() >= offsetTop) {
            $("#q-no").addClass("fixed");
            $("#time-2").show();
        } else {
            $("#q-no").removeClass("fixed");
            $("#time-2").hide();
        }
    });
    $("#to-top").click(function () {
        $("html, body").animate({
            scrollTop: "0px"
        }, {
            duration: 300,
            easing: "swing"
        });
    });
}
function loading() {
    $("body").append("<div id=\"loading\"><div id=\"loading-center\"><div id=\"loading-center-absolute\"><div class=\"object\" id=\"object_one\"></div><div class=\"object\" id=\"object_two\"></div><div class=\"object\" id=\"object_three\"></div><div class=\"object\" id=\"object_four\"></div></div></div></div>");
    $("body").ajaxStart(function () {
    });
    $("#loading").fadeIn(200);
    $("body").ajaxStop(function () {
        $("#loading").hide();
        $("#load-content").show();
        foot();
    });

}
function search() {
    var type = "all";
    //添加高级搜索
    $(".search").append("<div id=\"search-type\"><div class=\"search-type-li sel\" id=\"all\">搜索全站</div><div class=\"search-type-li\" id=\"Video-t\">搜索录像教学</div><div class=\"search-type-li\" id=\"Doc-t\">搜索自学材料</div><div class=\"search-type-li\" id=\"Flash-t\">搜索算法动画</div><div class=\"search-type-li\" id=\"Train-t\">搜索练习</div><div class=\"search-type-li\" id=\"Exam-t\">搜索测试</div><div id=\"pack-up\">收起</div></div>");
    //点击输入框弹出高级搜索
    $(".search-input input").click(function () {
        $("#search-type").slideDown(300);
    });
    //收起高级搜索
    $("#pack-up").click(function () {
        $("#search-type").slideUp(200);
    });
    //选择搜索类型
    $(".search-type-li").click(function () {
        $(".search-type-li").removeClass("sel");
        $(this).addClass("sel");
        type = $(this).attr("id");
        $(".search-input input").attr("placeholder", $(this).text());
        $("#pack-up").click();
        $(".search-input input").focus();
    });
    //进行搜索
    $(".search-btn").click(function () {
        if ($(".search-input input").val() == '') {
            alert("请输入搜索内容！")
        } else {
            location.href = "s_result.html?type=" + type + "&kw=" + $(".search-input input").val();
        }
    });
    //监听回车
    $(".search-input input").keyup(function (e) {
        //回车
        if (e.which == 13) {
            $(".search-btn").click();
        }
    });
}
function foot() {
    var height = $(window).height();
    var top = height - 126;
    if ($("footer").offset().top < top) {
        $("footer").css({
            width: '100%',
            position: 'fixed',
            bottom: 0
        })
    }
}

//初始化考试

//初始化章节
function initChapter(chaNOLast) {
    $.ajax({
        type: "post",
        url: defaultUrl + "getChapterListByChaNoLast",
        data: {
            chaNoLast: chaNOLast
        },
        success: function (j) {
            var json = JSON.parse(j).data;
            for (var i = 0; i < json.length; i++) {
                $('.part1').append("<li id=\"" + json[i].chaNo + "\">第 " + (i + 1) + " 章 " + json[i].chaName + "</li>");
            }
            addFilterTip(2);
            $(".part1 li").click(function () {
                $('.part2').empty();
                $('.part3').empty();
                $('.part4').empty();
                addFilterTip(3);
                $('.part2').append("<div class=\"filter-tip\">玩命加载中...</div>");
                $(this).addClass("filter-li-sel").siblings().removeClass("filter-li-sel");
                initChapterPart(2, $(this));
            });
        },
        error: function () {
            alert("error");
        }
    });
}
function initChapterPart(index, s) {
    $.ajax({
        type: "post",
        url: defaultUrl + "getChapterListByChaNoLast",
        data: {
            chaNoLast: s.attr("id")
        },
        success: function (j) {
            var json = JSON.parse(j).data;
            $('.part' + index).empty();
            if (json.length == 0) {
                $('.part' + index).append("<li id=\"" + s.attr("id") + "\">" + 1 + "、" + s.text().substring(2) + "</li>");
            }
            for (var i = 0; i < json.length; i++) {
                $('.part' + index).append("<li id=\"" + json[i].chaNo + "\">" + (i + 1) + "、" + json[i].chaName + "</li>");
            }
            $(".part2 li").click(function () {
                $('.part3').empty();
                $('.part4').empty();
                addFilterTip(4);
                $('.part3').append("<div class=\"filter-tip\">玩命加载中...</div>");
                $(this).addClass("filter-li-sel").siblings().removeClass("filter-li-sel");
                initChapterPart(3, $(this));
            });
            $(".part3 li").click(function () {
                $('.part4').empty();
                $('.part4').append("<div class=\"filter-tip\">玩命加载中...</div>");
                $(this).addClass("filter-li-sel").siblings().removeClass("filter-li-sel");
                other($(this));
            });
        },
        error: function () {
            alert("error");
        }
    });
}
function addFilterTip(index) {
    for (var i = index; i < 5; i++) {
        $('.part' + i).empty().append("<div class=\"filter-tip\">请选择上级目录</div>");
    }
}

//获取地址栏参数
function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return decodeURI(r[2]);
    return null;
}

//获取页面名称
function pageName() {
    var strUrl = location.href;
    var arrUrl = strUrl.split("/");
    var strPage = arrUrl[arrUrl.length - 1];
    return strPage;
}

//重写提示方法
function alert(content) {
    $("body").append("<div class=\"alert-bg\"><div class=\"alert\">" + content + "</div></div>");
    $(".alert-bg").fadeIn(300);
    $(".alert").slideDown(300);
    $(".alert-bg").click(function () {
        $(".alert").slideUp(300);
        $(this).fadeOut(300);
        setTimeout(function () {
            $(".alert-bg").remove();
        }, 300);
    });
}

//延时加载
function delay() {
    $.ajax({
        type: "post",
        url: defaultUrl + "setTime",
        success: function (j) {
        },
        error: function () {
            alert("error");
        }
    });
}

//空请求触发器
function nullAjax() {
    $.ajax({
        type: "post",
        url: defaultUrl + "nullAjax",
        success: function (j) {
        },
        error: function () {
            alert("error");
        }
    });
}

//倒计时
function GetRTime(t) {
    localStorage.setItem("remain-time", t);
    if (t >= 0) {
        var h = Math.floor(t / 60 / 60 % 24);
        var m = Math.floor(t / 60 % 60);
        var s = Math.floor(t % 60);
        $(".t_h").text(h < 10 ? "0" + h : h);
        $(".t_m").text(m < 10 ? "0" + m : m);
        $(".t_s").text(s < 10 ? "0" + s : s);
        setTimeout(function () {
            GetRTime(--t);
        }, 1000);
    } else {
        alert("考试时间到,试卷提交中...");
        setTimeout(function () {
            handIn();
        }, 1000)
    }
}