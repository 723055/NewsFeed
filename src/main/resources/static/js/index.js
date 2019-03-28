        $(document).ready(function () {
            console.log("ready!");
            $.getJSON("/api/news/newsList", function (result) {
                console.log(result);
                var select = $("#allnews");
                for (var i = 0; i < result.length; i++) {
                    var element = result[i];
                    console.log(element);
                    select.append($("<br> <b></b>")
                        .attr("value", element["id"])
                        .text(element["nameTopic"])).append($("<p></p>")
                        .attr("value", element["id"])
                        .text(element["content"])).append($("<p></p>")
                        .attr("value", element["id"])
                        .text(element["publicationDate"] + "\u00a0\u00a0\u00a0\u00a0" + element["category"]["name"]));
                }
            });


            $.getJSON("/api/categories/all", function (result) {
                console.log(result);
                var select = $("#searchCategory");
                select.append($("<option></option>"));
                for (var i = 0; i < result.length; i++) {
                    var element = result[i];
                    select.append($("<option></option>")
                        .attr("value", element["id"])
                        .text(element["name"]));
                }
            });

            $("#btnSearch").click(function (event) {
                event.preventDefault();
                ajaxGet();
                ajaxGetContext();
            });

            function ajaxGet() {

                var formData = {
                    "nameTopic": $("#searchTopic").val(),
                    "content": $("#searchContent").val(),
                    "category": $("#searchCategory").val()
                }

                $.ajax({
                    type: "GET",
                    url: "api/news/newsList/topic/" + formData.nameTopic,
                    success: function (result) {
                        if (Object.keys(result).length > 0) {
                            var select = $("#allnews");
                            select.empty();
                            console.log(typeof result, result);

                            for (var i = 0; i < result.length; i++) {
                                var element = result[i];
                                console.log(element);
                                select.append($("<br> <b></b>")
                                    .attr("value", element["id"])
                                    .text(element["nameTopic"])).append($("<p></p>")
                                    .attr("value", element["id"])
                                    .text(element["content"])).append($("<p></p>")
                                    .attr("value", element["id"])
                                    .text(element["publicationDate"] + "\u00a0\u00a0\u00a0\u00a0" + element["category"]["name"]));
                            }
                        }
                    }
                });
            }

            function ajaxGetContext() {
                var formData = {
                    "nameTopic": $("#searchTopic").val(),
                    "content": $("#searchContent").val(),
                    "category": $("#searchCategory").val()
                }
                $.ajax({
                    type: "GET",
                    url: "api/news/newsList/content/" + formData.content,
                    success: function (result) {
                        if (Object.keys(result).length > 0) {
                            var select = $("#allnews");
                            select.empty();
                            console.log(typeof result, result);

                            for (var i = 0; i < result.length; i++) {
                                var element = result[i];
                                console.log(element);
                                select.append($("<br> <b></b>")
                                    .attr("value", element["id"])
                                    .text(element["nameTopic"])).append($("<p></p>")
                                    .attr("value", element["id"])
                                    .text(element["content"])).append($("<p></p>")
                                    .attr("value", element["id"])
                                    .text(element["publicationDate"] + "\u00a0\u00a0\u00a0\u00a0" + element["category"]["name"]));

                            }
                        }
                    }
                });
            }
        });
