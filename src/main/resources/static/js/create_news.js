$(document).ready(function () {
    console.log("ready!");

    $.getJSON("/api/categories/all", function (result) {
        console.log(result);
        var select = $("#category");
        for (var i = 0; i < result.length; i++) {
            var element = result[i];
            select.append($("<option></option>")
                .attr("value", element["id"])
                .text(element["name"]));
        }
    });

    // SUBMIT FORM

    $("#setNews").submit(function (event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });

    function ajaxPost() {
        // PREPARE FORM DATA
        var nowDate = new Date().toLocaleDateString;

        var formData = {
            "nameTopic": $("#nameTopic").val(),
            "content": $("#content").val(),
            "category": $("#category").val()
        }

        // DO POST
        $.ajax({
            "type": 'POST',
            "contentType": 'application/json',
            "url": "api/news/" + formData.nameTopic + "/" + formData.content + "/" + formData.category,
            "data": JSON.stringify(formData),
            "dataType": 'json',
            "success": function (result) {
                console.log(result);
                alert("Ваша новость сохранена. Для продолжения нажмите ОК");
            },
            error: function (e) {
                alert("Error!")
                console.log("ERROR: ", e);
            }
        });

    }

});
