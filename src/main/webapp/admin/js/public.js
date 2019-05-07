/**
 * Created by 耿飞 on 2016/3/31.
 */

//var defaultUrl = "http://jsjwhj.oicp.net/active/";
var defaultUrl = "http://localhost:8080/active/";

//初始化页面
function initHtml() {
    //loading();
    leftNav();
    topNav();
    mainFrame();
    getChapter();
}
function checkPower(){
	var tno = localStorage.getItem("tno");
	if(tno  != "admin"){
		return false;
	} else {
		return true;
	}
}
function loading() {
    $("body").ajaxStart(function () {
        $("#loading").fadeIn(200);
    });
    $("body").ajaxStop(function () {
        $("#loading").hide();
        $("#frame-content").fadeIn(100);
    });
}
function leftNav() {
    //获取配置
    var navList = getConfig("config/leftNav.json");
    //初始化
    for (var i = 0; i < navList.length; i++) {
        var id = "navigation-left-li" + i;
        $("#left-bar ul").append("<li class='big' id='" + id + "'><a class='big_s' href='" + navList[i].href + "'><i class='fa left-i'></i>" + navList[i].name + "<i class='fa right-i fa-" + navList[i].icon + "'></i></a></li>");
        if (navList[i].child.length > 0) {
            $("#" + id + " .left-i").removeClass("fa-circle").addClass("fa-caret-right");
            $("#" + id).append("<div class='lit_d'></div>");
            for (var j = 0; j < navList[i].child.length; j++) {
                $("#" + id + " .lit_d").append("<a class='little' href='" + navList[i].child[j].href + "'><i class='fa fa-" + navList[i].child[j].icon + "'></i>" + navList[i].child[j].name + "</a>");
            }
        }
    }
    $('.big_s').click(function () {
        if ($(this).children(".left-i").hasClass("fa-caret-right")) {
            $(this).children(".left-i").removeClass("fa-caret-right").addClass("fa-caret-down")
        } else if ($(this).children(".left-i").hasClass("fa-caret-down")) {
            $(this).children(".left-i").removeClass("fa-caret-down").addClass("fa-caret-right")
        }
        if ($(this).next().css("display") == "none") {
            //$('.lit_d').slideUp(200);
            $(this).next().slideDown(200);
        } else {
            $(this).next().slideUp(200);
        }
    });
    //事件
    $('#left-bar a').click(function () {
        if ($(this).attr("href") == "#") {
            return;
        } else {
            var pageName = getPageName($(this).attr("href"));
            var pageId = getPageIdByPageName(pageName);
            if ($("#" + pageId).length == 0) {
                initFrame(this);
            } else {
                changeTab(getTabIdByPageName(pageName));
                changePage(pageId);
            }
        }
    });
}
function topNav() {
    //获取配置
    var navList = getConfig("config/topNav.json");
    //初始化
    for (var i = 0; i < navList.length; i++) {
        var id = "navigation-li" + i;
        $(".nav").append("<li id='" + id + "'><a class='nav-li' href='" + navList[i].href + "'>" + navList[i].name + "</a></li>");
        if (navList[i].child.length > 0) {
            $("#" + id).append("<div class='sub-child'></div>");
            for (var j = 0; j < navList[i].child.length; j++) {
                $("#" + id + " .sub-child").append("<a href='" + navList[i].child[j].href + "'>" + navList[i].child[j].name + "</a>");
            }
        }
    }
    //用户相关
    $(".nav").append("<li id='username-top'><a class='nav-li' href='#'>当前用户：" + localStorage.getItem("tname") + "</a></li>");
    $("#username-top").append("<div class='sub-child'></div>");
    $("#username-top .sub-child").append("<a id='login-out' style='cursor: pointer' onclick='exit()'>退出登录</a>");

    $('.nav li').hover(function () {
        $(this).children('a').toggleClass('sel');
        $(this).children('.sub-child').slideDown(300).filter(':not(:animated)');
    }, function () {
        $(this).children('a').toggleClass('sel');
        $(this).children('.sub-child').stop().slideUp(100);
    });
}
function mainFrame() {
    $("body").append("<div id=\"loading\"><div id=\"loading-center\"><div id=\"loading-center-absolute\"><div class=\"object\" id=\"object_one\"></div><div class=\"object\" id=\"object_two\"></div><div class=\"object\" id=\"object_three\"></div><div class=\"object\" id=\"object_four\"></div></div></div></div>");
    //首页
    var pageName = "main";
    var pageId = getPageIdByPageName(pageName);
    //添加一个页面
    $("#frame-content").append("<div class=\"page-block\" id=\"" + pageId + "\"></div>");
    //获取page
    $.ajax({
        async: false,
        url: pageName + ".html",
        success: function (html) {
            $("#" + pageId).html(html);
            initThis();
        }
    });
    if (location.href.indexOf("#") != -1 && location.href.indexOf("#") + 1 != location.href.length) {
        initFrame(null, location.href);
    }
}

//加载一个页面块
function initFrame(dom, b_href) {
    if (dom == null) {
        var href = getHref(b_href);
        var pageName = getPageName(href).split(".")[0];
        dom = $(".big a[href='#" + pageName + "']");
    }
    var href = getHref($(dom).attr("href"));
    var pageName = getPageName($(dom).attr("href"));
    var pageId = getPageIdByPageName(pageName);
    //隐藏其他块
    $(".page-block").hide();
    //添加一个页面
    $("#frame-content").append("<div class=\"page-block\" id=\"" + pageId + "\"></div>");
    addTab(dom);
    //tab修正
    $("#frame-content").css("top", (50 + $("#tab-view").outerHeight()));
    //获取page
    $.ajax({
        async: false,
        url: href,
        cache: false,
        success: function (html) {
            $("#" + pageId).html(html);
            initThis();
        }
    });
}

function addTab(dom) {
    var tabName = $(dom).text();
    var tabId = getPageName($(dom).attr("href")) + "-tab";
    $("#tab-view").append("<li class=\"tab-li\" id='" + tabId + "'>" + tabName + "<i class=\"fa fa-close\" id='" + tabId + "-close'></i></li>")
    changeTab(tabId);
    $(".tab-li").click(function () {
        var tabId = $(this).attr("id");
        changeTab(tabId);
        changePage(getPageIdByTabId(tabId));
    })
    $("#" + tabId + "-close").click(function () {
        var tabCloseId = $(this).attr("id");
        closeTab(tabCloseId.substr(0, tabCloseId.length - 6));
        closePage(getPageIdByTabCloseId(tabCloseId));
    })
}

function getPageIdByTabId(tabId) {
    var pageName = tabId.substr(0, tabId.length - 4);
    return getPageIdByPageName(pageName);
}
function changeTab(tabId) {
    $(".tab-li").removeClass("sel");
    $("#" + tabId).addClass("sel");
    var pageName = tabId.substr(0, tabId.length - 4);
    location.href = "#" + pageName;
}
function changePage(pageId) {
    $(".page-block").hide();
    $("#" + pageId).show();
}

function getPageIdByTabCloseId(tabCloseId) {
    var pageName = tabCloseId.substr(0, tabCloseId.length - 10);
    return getPageIdByPageName(pageName);
}
function closeTab(tabId) {
    if (!$("#" + tabId).hasClass("sel")) {
        $("#" + tabId).remove();
        //tab修正
        $("#frame-content").css("top", (50 + $("#tab-view").outerHeight()));
    } else {
        var bTabId = $("#" + tabId).prev().attr("id");
        $("#" + tabId).remove();
        //tab修正
        $("#frame-content").css("top", (50 + $("#tab-view").outerHeight()));
        changeTab(bTabId);
        changePage(getPageIdByTabId(bTabId));
    }
}
function closePage(pageId) {
    $("#" + pageId).remove();
}

function getTabIdByPageName(pageName) {
    return pageName + "-tab";
}
function getPageIdByPageName(pageName) {
    return pageName + "-div-content";
}

function getKnowledgeName(knowledgeId){
	var name;
	$.ajax({
		type:"post",
		async:false,
		url:defaultUrl + "getKnowledgeName",
		data:{
			id:knowledgeId
		},
		success:function(j){
			var json = JSON.parse(j).data;
			name = json[0].knowledge;
		},
		error:function(){
			alert("error");
		}
	});
	return name;
}
//初始化章节
function initChapter(chaNoLast, pageName, partIndex) {
    if (chaNoLast == -1) {
        $("#" + pageName + "-div-content .chapter2").empty().append("<option value='" + chaNoLast + "'>不限</option>");
        $("#" + pageName + "-div-content .chapter3").empty().append("<option value='" + chaNoLast + "'>不限</option>");
        return;
    }
    $.ajax({
        type: "post",
        async: false,
        url: defaultUrl + "getChapterListByChaNoLast",
        data: {
            chaNoLast: chaNoLast
        },
        success: function (j) {
            var json = JSON.parse(j).data;
            if (partIndex == 2) {
                $("#" + pageName + "-div-content .chapter" + 3).empty();
            }
            $("#" + pageName + "-div-content .chapter" + partIndex).empty();
            if (json.length == 0) {
                //$("#" + pageName + "-div-content .chapter" + partIndex).attr("disabled", true);
                $("#" + pageName + "-div-content .chapter" + partIndex).append("<option value='" + chaNoLast + "'>已选择</option>")
            }
            for (var i = 0; i < json.length; i++) {
                $("#" + pageName + "-div-content .chapter" + partIndex).append("<option value='" + json[i].chaNo + "'>" + json[i].chaName + "</option>")
            }
        },
        error: function () {
            alert("error");
        }
    });
}
function getNext(val, pageName, partIndex) {
    initChapter(val, pageName, partIndex);
}
//获取章节
var chapter;
function getChapter() {
    $.ajax({
        type: "post",
        async: false,
        url: defaultUrl + "getAllChapter",
        success: function (j) {
            chapter = JSON.parse(j).data;
        },
        error: function () {
            alert("error");
        }
    });
}
function getChaName(chaNo) {
	for(var i = 0; i < chapter.length; ++i){
		if(chapter[i].chaNo == chaNo)
			return chapter[i].chaName;
	}
}
//获取题目类型名
function getQTypeName(qType) {
    if (qType == 1) {
        return "单项选择";
    }
    if (qType == 2) {
        return "多项选择";
    }
    if (qType == 3) {
        return "填空";
    }
    if (qType == 4) {
        return "判断";
    }
    if (qType == 5) {
        return "简答";
    }
}
//获取题目难度名
function getQDifficultyName(qDifficulty) {
    if (qDifficulty == 1) {
        return "简单";
    }
    if (qDifficulty == 2) {
        return "中等";
    }
    if (qDifficulty == 3) {
        return "困难";
    }
}
//获取练习类型名
function getTrainTypeName(trainType) {
    if (trainType == 1) {
        return "普通练习";
    }
    if (trainType == 2) {
        return "家庭作业";
    }
}
//获取页面标识符
function getPageName(href) {
    var arrUrl = href.split("#");
    arrUrl = arrUrl[arrUrl.length - 1].split("?");
    return arrUrl[0];
}
//获取页面地址
function getHref(href) {
    return getPageName(href) + ".html";
}

//登出
function exit() {
    if (confirm("确认退出登陆？")) {
        localStorage.removeItem("tno");
        localStorage.removeItem("tname");
        localStorage.removeItem("loginTime");
        location.href = "login.html";
    }
}
//读取配置文件
function getConfig(href) {
    var config;
    $.ajax({
        async: false,
        type: "get",
        url: href,
        success: function (j) {
            config = j.config;
        },
        error: function () {
            alert("error");
        }
    });
    return config;
}
//获取地址栏参数
function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return decodeURI(r[2]);
    return null;
}
//重写提示方法
function alert2(content) {
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
function pop(content) {
    $("body").append("<div class=\"alert-bg\"><div class=\"pop\">" + content + "</div><div class='pop-close'><i class='fa fa-close'></i></div></div>");
    $(".alert-bg").fadeIn(300);
    $(".pop-close").click(function () {
        $(this).parent().remove();
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