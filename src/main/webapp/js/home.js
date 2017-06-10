/**
 * Created by yyy on 2017/6/10.
 */

var PlaneList = {
    init:function() {
        this.gridsFather = $("#list-part");
        this.lastGrid = $(".list-item").eq(0);
    },
    updateData:function(planeList){
        this.gridsFather.empty();
        var _this = this;
        $.each(planeList,function(i,plane){
            var tempGrid = _this.lastGrid.clone(true);
            tempGrid.find(".company-name").eq(0).text(plane.company);
            tempGrid.find(".flight-num").eq(0).text(plane.flightNum);
            tempGrid.find(".depart-time").eq(0).text(plane.departingTime);
            tempGrid.find(".arriving-time").eq(0).text(plane.arriving-time);
            tempGrid.find(".depart-city").eq(0).text(plane.departingAirport);
            tempGrid.find(".arriving-city").eq(0).text(plane.arrivingAirport);
            tempGrid.find(".punctual-rate").eq(0).text(plane.punctualRate);
            tempGrid.find(".lowest-price").eq(0).text(plane.lowestPrice);

            var gridsFather = tempGrid.find(".price-list").eq(0);
            PlatformList.init(gridsFather);
            PlatformList.updateData(plane.dataSource);

            tempGrid.find(".btn-detail").eq(0).attr("href",plane.departDate+" "+plane.departTime);

            _this.gridsFather.append(tempGrid);
        })
    }
}

var PlatformList = {
    init:function( gridsFather){
        this.gridsFather = gridsFather;
        this.lastGrid = $(".price-item").eq(0);
    },
    updateData:function(platformList){
        this.gridsFather.empty();
        var _this = this;
        $.each(platformList,function(i,platform){
            var tempGrid = _this.lastGrid.clone(true);
            tempGrid.find(".platform").eq(0).text(platform.platformName);
            tempGrid.find(".p-price").eq(0).text(platform.lowestPrice);
            tempGrid.find(".link").eq(0).attr("href",platform.link);

            _this.gridsFather.append(tempGrid);
        })

    }
}


$(document).ready(
    function(){

        PlaneList.init();
        $.get({
            url:"/home",
            success:function(list){
                PlaneList.updateData(list);
            },
            error:function(){
                console.log("get default list wrong!");
            }
        })

        var query = $("#query");
        query.click(function(){
            var departDate = $("#departDate");
        })
    }
)