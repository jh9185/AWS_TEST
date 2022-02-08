function initCount() {
    transValue = 0;
    pathCount = 0;
    locations.length = 0;
    markers.length = 0;
    busmarkers.length = 0;
    infoWindows.length = 0;
    businfoWindows.length = 0;
    polylinePaths.length =0;
}
function insertLocation(locations, seq, x, y) {
    locations.push({location: seq, lat: y, lng: x});
}

function insertMarker(markers, x, y) {
    let marker = new naver.maps.Marker({
        map: map,
        position: new naver.maps.LatLng(y, x)
    });

    markers.push(marker);// 생성한 마커를 배열에 담는다.
}

function insertInfoWindow(infoWindows, seq, stationNm) {
    var myaddress = seq + " " + stationNm;

    //info
    let infoWindow = new naver.maps.InfoWindow({
        content: '<div style="width:150px;text-align:center;padding:5px;"><b>' + myaddress + '</b><br></div>'
    }); // 클릭했을 때 띄워줄 정보 HTML 작성

    infoWindows.push(infoWindow); // 생성한 정보창을 배열에 담는다.
}
function insertBusinfoWindows(plainNo) {
    //info
    let infoWindow = new naver.maps.InfoWindow({
        content: '<div style="width:150px;text-align:center;padding:5px;"><b>' + plainNo + '</b><br></div>'
    }); // 클릭했을 때 띄워줄 정보 HTML 작성

    businfoWindows.push(infoWindow); // 생성한 정보창을 배열에 담는다.
}

function insertPolyline(polylinePaths, posX, posY) {
    //poly
    let polyPath = new naver.maps.LatLng(posX, posY);
    polylinePaths.push(polyPath);

    pathCount++;
}

function insertBusMarker(busmarkers, posX, posY) {
    var position = new naver.maps.LatLng(posX, posY);

    var markerOptions = new naver.maps.Marker({
        position: position.destinationPoint(50, 15),
        map: map,
        icon: {
            url: '/icon/bus.png',
            size: new naver.maps.Size(50, 50),
            origin: new naver.maps.Point(0, 0),
            anchor: new naver.maps.Point(25, 26)
        }
    });

    busmarkers.push(markerOptions);
}


function getClickHandler(seq) {

    return function(e) {  // 마커를 클릭하는 부분
        let marker = markers[seq], // 클릭한 마커의 시퀀스로 찾는다.
            infoWindow = infoWindows[seq]; // 클릭한 마커의 시퀀스로 찾는다

        if (infoWindow.getMap()) {
            infoWindow.close();
        } else {
            infoWindow.open(map, marker); // 표출
        }
    }
}
function getClickBusHandler(seq) {

    return function(e) {  // 마커를 클릭하는 부분
        let marker = busmarkers[seq], // 클릭한 마커의 시퀀스로 찾는다.
            infoWindow = businfoWindows[seq]; // 클릭한 마커의 시퀀스로 찾는다

        if (infoWindow.getMap()) {
            infoWindow.close();
        } else {
            infoWindow.open(map, marker); // 표출
        }
    }
}