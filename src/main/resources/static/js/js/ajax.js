function ajax(URL, method, functionName, dataArray) {
    let xhttp = new XMLHttpRequest();
    xhttp.open(method, URL, true);
    xhttp.setRequestHeader("content type");
    xhttp.send(requestData(dataArray);

        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                functionName(this);
            }
        }
    }

    function requestData(dataArr) {
        let out = '';
        for (let key in dataArr) {
            out += ${key} = ${dataArr[key]}&;
        }
        console.log(out);
        return out;
    }
