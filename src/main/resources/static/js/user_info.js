var url;

function formatTrueName(value, row, index) {
    return value; // 默认情况下返回原始值
}

function reloadCurGrid() {
    console.log("reloadCurGrid()");
    resetValue();
    resetSelectValue();
    searchUser();
}

function searchUser() {
    $("#dg").datagrid('load', {
        "userName": $("#search-userName").val(),
        "trueName": $("#search-trueName").val(),
        "email": $("#search-email").val()
    });
}

function openUserAddDialog() {
    $("#dlg").dialog("open").dialog("setTitle", "添加用户信息");
    url = "/saveUser";
}

function openUserModifyDialog() {
    var selectedRows = $("#dg").datagrid("getSelections");//得到 datagrid 选择的行
    if (selectedRows.length != 1) {
        $.messager.alert("系统提示", "请选择一条要编辑的数据！");
        return;
    }
    var row = selectedRows[0];//把选中的行赋值给row 变量
    $("#dlg").dialog("open").dialog("setTitle", "编辑用户信息");
    $("#UserForm").form("load", row);//让id=UserForm的某物的表单加载row的值
    url = "/saveUser?id=" + row.id;
}

function saveUser() {
    var requestData = {
        userName: $('#userName').val(),
        trueName: $('#trueName').val(),
        password: $('#password').val(),
        email: $('#email').val(),
        phone: $('#phone').val(),
        roleName: $('#roleName').combobox('getValue')
    };
    $.ajax({
        url: url, // 验证的后端接口地址
        type: 'POST',
        dataType: 'json',
        data: requestData,
        success: function (response) {
            console.log(response);
            if (response.success) {
                resetValue();
                $("#dlg").dialog("close");
                $("#dg").datagrid("reload");
                $.messager.alert("系统提示", response.message);
            } else {
                $.messager.alert("系统提示", response.message);
            }
        },
        error: function (xhr, status, error) {
            console.log('请求发生错误: ' + error);
        }
    });
}

function resetSelectValue() {
    $("#search-userName").val("");
    $("#search-email").val("");
    $("#search-trueName").val("");
}

function resetValue() {
    $('#userName').val("");
    $('#trueName').val("");
    $('#password').val("");
    $('#email').val("");
    $('#phone').val("");
    $('#roleName').combobox('setValue');
}


function closeUserDialog() {
    $("#dlg").dialog("close");
    resetValue();
}

function deleteUser() {
    var selectedRows = $("#dg").datagrid("getSelections");
    if (selectedRows.length == 0) {
        $.messager.alert("系统提示", "请选择要删除的数据！");
        return;
    }
    var strIds = [];
    for (var i = 0; i < selectedRows.length; i++) {
        strIds.push(selectedRows[i].id);
    }
    var ids = strIds.join(",");
    $.messager.confirm("系统提示", "您确定要删除这<font color=red>" + selectedRows.length + "</font>条数据吗？", function (r) {
        if (r) {
            $.post("/deleteUser", {ids: ids}, function (result) {
                if (result.success) {
                    $.messager.alert("系统提示", "数据已成功删除！");
                    $("#dg").datagrid("reload");
                } else {
                    $.messager.alert("系统提示", "数据删除失败，请联系系统管理员！");
                }
            }, "json");
        }
    });
}