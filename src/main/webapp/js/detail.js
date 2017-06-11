/**
 * Created by yyy on 2017/6/11.
 */

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

var DetailPlatformList = {
    init:function(){
        this.gridsFather=$("#detail-platform-list");
        this.lastGrid = $(".detail-platform-item").eq(0);
        this.hr = $(".interval").eq(0);
    },

    updateData:function(platformList){
        this.gridsFather.empty();
        var _this = this;
        $.each(platformList,function(i,platform){
            var tempGrid = _this.lastGrid.clone(true);
            tempGrid.find(".platform").eq(0).text(platform.platformName);
            tempGrid.find(".p-price").eq(0).text(platform.lowestPrice);
            tempGrid.find(".link").eq(0).attr("href",platform.link);

            var priceFather = tempGrid.find(".detail-price-list").eq(0);

            DetailPriceList.init(priceFather);
            DetailPriceList.updateData(platform.priceVOS);

            _this.gridsFather.append(_this.hr);
            _this.gridsFather.append(tempGrid);

        })

        _this.gridsFather.append(_this.hr);
    }
}

var DetailPriceList = {
    init:function(gridsFather){
        this.gridsFather=gridsFather;
        this.lastGrid = gridsFather.find(".detail-price-item").eq(0);
    },
    updateData:function(priceList){
        this.gridsFather.empty();
        var _this = this;
        $.each(priceList,function(i,price){
            var tempGrid = _this.lastGrid.clone(true);
            tempGrid.find(".detail-price").eq(0).text(price.price);
            tempGrid.find(".detail-note").eq(0).text(price.note);

            _this.gridsFather.append(tempGrid);
        })
    }
}


$(document).ready(
    function(){
        var flightNum = getUrlParam("flightNum");
        var departTime = getUrlParam("departTime");

        $.get({
            url:"/home/detail",
            data:{
                flightNum:flightNum,
                departTime:departTime
            },
            success:function(plane){
                $("#departCity").text(plane.departure);
                $("#arrivingCity").text(plane.destination);
                $("#departDate").text(plane.departingDate);
                $("#arrivingDate").text(plane.arrivingDate);
                $("#detail-company").text(plane.company);
                $("#detail-flightNum").text(plane.flightNum);
                $("#detail-depart-time").text(plane.departingTime);
                $("#detail-arriving-time").text(plane.arrivingTime);
                $("#detail-depart-airport").text(plane.departingAirport);
                $("#detail-arrive-airport").text(plane.arrivingAirport);
                $("#detail-rate").text(plane.punctualRate);
                $("#detail-lowest").text(plane.lowestPrice);

                DetailPlatformList.init();
                DetailPlatformList.updateData(plane.dataSource);


            }
        })

    }
)
