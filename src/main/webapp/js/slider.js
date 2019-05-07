var autoDisplay = true;

$(function () {
    initSlider();
    display();
});

function initSlider() {

    var imgWidth = $(".slider").parent().width();
    var imgNum = $(".slider li").length;
    var allWidth = imgWidth * imgNum;
    var move = 0;
    var time = 500;
    var index = 0;

    //初始化轮播图
    {
        $(".slider").css("width", allWidth).children("li").css("width", imgWidth);
    }

    //初始化预览窗口
    {
        for (var i = 0; i < imgNum; i++) {
            $(".slider-preview").append("<li><div></div></li>");
            var s = $(".slider li").eq(i).css("background-image");
            $(".slider-preview li div").eq(i).css({
                "background-image": s,
                "height": "100%"
            })
        }
        $(".slider-preview li").eq(0).children().addClass("preview-select");
        var preImgWidth = (imgWidth - 4) / imgNum;
        $(".slider-preview").css({
            "margin-top": 0 - preImgWidth * 0.35 - 8
        }).children("li").css({
            "width": preImgWidth,
            "height": preImgWidth * 0.35
        });
    }

    //事件
    $("#slider-next").click(function () {
        index = (++index + imgNum) % imgNum;
        move = -imgWidth * index;
        moveAction();
    });
    $("#slider-last").click(function () {
        index = (--index + imgNum) % imgNum;
        move = -imgWidth * index;
        moveAction();
    });
    $(".slider-preview li").click(function () {
        index = $(this).index();
        move = -imgWidth * index;
        moveAction();
    });
    $(".slider-box").children().hover(function () {
        autoDisplay = false;
    }, function () {
        autoDisplay = true;
    });

    //动作
    function moveAction() {
        if (true) {
            $(".slider").css({
                "transition": time + "ms",
                "-moz-transition": time + "ms",
                "-webkit-transition": time + "ms",
                "-o-transition": time + "ms"
            });
            $(".slider").css({"margin-left": move});
        } else {
            $(".slider").animate({"margin-left": move}, time);
        }
        $(".slider-preview li").siblings().children().removeClass("preview-select");
        $(".slider-preview li").eq(index).children().addClass("preview-select");
    }
}

//轮播
function display() {
    setInterval(function () {
        if (autoDisplay) {
            $("#slider-next").click();
        }
    }, 3000);
}