function setFenceTrack(PointsArray,ID) {
    var json = {};
    var data = [];
    for (var i=0;i<PointsArray.length;i++) {
        var tempPoint = {};
        tempPoint.lon = PointsArray[i].lng;
        tempPoint.lat = PointsArray[i].lat;
        data.push(tempPoint);
    }
    json.id = ID;
    json.fenceTrack = data;
    //console.log(JSON.stringify(json));
    $.ajax(
        {
            url:"http://localhost:8080/demo1/setFenceTrack",
            type:"POST",
            data:JSON.stringify(json),
            contentType : "application/json",
            dataType:"text",
            success : function (data,status) {
                if(data=='success')
                    alert('ajax ok!');
            }
        }
    )
}