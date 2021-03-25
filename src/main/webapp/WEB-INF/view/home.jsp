<%--
  Created by IntelliJ IDEA.
  User: Harshal
  Date: 25-03-2021
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="us">
<head>
    <meta charset=utf-8>
    <meta name=viewport content="width=device-width,initial-scale=1">
    <title>Laxmi Pan</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900" rel=stylesheet>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div class="container-fluid" style="margin-top: 40px">
    <div class="row text-center justify-content-center">
        <div class="col-md-8 ">
            <div class="card">
                <div class="card-header bg-primary text--white" style="color: white">
                    Select Report Type
                </div>
                <div class="card-body">
                    <form action="/" method="post" id="form" onsubmit="return submitForm(this.event)">
                        <div class="form-group">
                            <label for="reportType">Choose Report</label>
                            <select onchange="renderComponent()" class="form-control" name="reportType" id="reportType"
                                    required>
                                <option value="" selected disabled>Choose Report</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                            </select>
                        </div>
                        <div id="report1" class="mt-2" hidden>
                            <div class="form-group">
                                <label for="report1DatePicker">Choose Date</label>
                                <input type="date" class="form-control" name="report1DatePicker"
                                       id="report1DatePicker"/>
                            </div>
                        </div>
                        <div id="report2" class="mt-2" hidden>
                            <div class="form-row">
                                <div class="col-md-6 col-sm-12 ma-3">
                                    <label for="report2StartDate">Start Date Time</label>
                                    <input type="datetime-local" class="form-control" name="report2StartDate"
                                           id="report2StartDate"/>
                                </div>
                                <div class="col-md-6 col-sm-12 ma-3">
                                    <label for="report2EndDate">End Date Time</label>
                                    <input type="datetime-local" class="form-control" name="report2EndDate"
                                           id="report2EndDate"/>
                                </div>
                            </div>
                        </div>
                        <div id="report3" class="mt-2" hidden>
                            <div class="form-row">
                                <div class="col-md-6 col-sm-12 ma-3">
                                    <label for="report3StartNumber">Start Number</label>
                                    <input type="number" min="0" step="1" class="form-control" name="report3StartNumber"
                                           id="report3StartNumber"/>
                                </div>
                                <div class="col-md-6 col-sm-12 ma-3">
                                    <label for="report3EndNumber">End Number</label>
                                    <input type="number" min="0" step="1" class="form-control" name="report3EndNumber"
                                           id="report3EndNumber"/>
                                </div>
                            </div>
                        </div>
                        <input type="submit" form="form" id="submitReport" placeholder="Submit"
                               class="btn btn-outline-primary mt-3"/>


                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script>

    function renderComponent() {
        var dd = document.getElementById("reportType").value;
        if (dd === "1") {
            document.getElementById("report1").hidden = false;
            document.getElementById("report2").hidden = true;
            document.getElementById("report3").hidden = true;
            changeRequired();
        } else if (dd === "2") {
            document.getElementById("report1").hidden = true;
            document.getElementById("report2").hidden = false;
            document.getElementById("report3").hidden = true;
            changeRequired();
        } else if (dd === "3") {
            document.getElementById("report1").hidden = true;
            document.getElementById("report2").hidden = true;
            document.getElementById("report3").hidden = false;
            changeRequired();
        } else {
            document.getElementById("report1").hidden = true;
            document.getElementById("report2").hidden = true;
            document.getElementById("report3").hidden = true;
            changeRequired();
        }
    }

    function changeRequired() {
        var dd = document.getElementById("reportType").value;
        if (dd === "1") {
            document.getElementById("report1DatePicker").required = true;
            document.getElementById("report2StartDate").required = false;
            document.getElementById("report2EndDate").required = false;
            document.getElementById("report3StartNumber").required = false;
            document.getElementById("report3EndNumber").required = false;
        } else if (dd === "2") {
            document.getElementById("report1DatePicker").required = false;
            document.getElementById("report2StartDate").required = true;
            document.getElementById("report2EndDate").required = true;
            document.getElementById("report3StartNumber").required = false;
            document.getElementById("report3EndNumber").required = false;
        } else if (dd === "3") {
            document.getElementById("report1DatePicker").required = false;
            document.getElementById("report2StartDate").required = false;
            document.getElementById("report2EndDate").required = false;
            document.getElementById("report3StartNumber").required = true;
            document.getElementById("report3EndNumber").required = true;
        } else {
            document.getElementById("report1DatePicker").required = true;
            document.getElementById("report2StartDate").required = true;
            document.getElementById("report2EndDate").required = true;
            document.getElementById("report3StartNumber").required = true;
            document.getElementById("report3EndNumber").required = true;
        }
    }

    function submitForm(event) {
        event.preventDefault();
        var dd = document.getElementById("reportType").value;
        if (dd === "1") {
            var date = document.getElementById("report1DatePicker").value;
            console.log(date)
        } else if (dd === "2") {
            var startDateTime = document.getElementById("report2StartDate").value;
            var endDateTime = document.getElementById("report2EndDate").value;
            console.log(startDateTime);
            console.log(endDateTime);
        } else if (dd === "3") {
            var startNumber = document.getElementById("report3StartNumber").value;
            var endNumber = document.getElementById("report3EndNumber").value;
            console.log(startNumber);
            console.log(endNumber);
        } else {
            alert("INVALID REPORT TYPE SELECTION");
        }
    }

</script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"
        integrity="sha384-LtrjvnR4Twt/qOuYxE721u19sVFLVSA4hf/rRt6PrZTmiPltdZcI7q7PXQBYTKyf"
        crossorigin="anonymous"></script>
<script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>

</body>
</html>
