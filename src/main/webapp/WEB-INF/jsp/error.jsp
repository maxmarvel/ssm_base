<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>失败提示信息</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
</head>
<body>
<table class="submit_hint">
    <tr>
        <td>
            <div id="tabBtnContainer" width="100%">
                <ul id="tabBtnUi">
                    <li><a href="#"></a>失败提示信息</li>
                </ul>
            </div>
        </td>
    </tr>
    <tr>
        <td style="font-size: 14px; color: #ff0000; padding-top: 2px;"
            align='center' valign="middle"><img alt=""
                                                src="${ctx}images/info.png" align="middle" height="30"
                                                width="30"/> <span style="vertical-align: middle">${exceptionResultInfo.message}</span>
        </td>
    </tr>
</table>
<table width='100%'>
    <tr>
        <td align="center"><input type="button" align="middle"
                                  class="button" value="确定" onclick="history.go(-1);"/></td>
    </tr>
</table>
</body>
</html>