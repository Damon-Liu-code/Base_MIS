var editingId;

function edit(index) {
    if (editingId != undefined) {
        $('#easy-editRole').datagrid('endEdit', editingId);
    }
    var row = $('#easy-editRole').datagrid('getSelected');
    if (row) {
        editingId = index;
        $('#easy-editRole').datagrid('beginEdit', editingId);
    } else {
        $.messager.alert("系统提示", "请选择一条要编辑的数据！");
    }
}


function save() {
    var changeRows = $('#easy-editRole').datagrid('getChanges');
    console.log("save()");
    if (changeRows) {
        if (editingId != undefined) {
            $('#easy-editRole').datagrid('endEdit', editingId);
            var rows = $('#easy-editRole').datagrid('getRows');
            var dataRows = new Array();
            for (var i = 0; i < rows.length; i++) {
                dataRows[i] = {id: rows[i].id, roleName: rows[i].roleName, roleNumber: rows[i].roleNumber}
                var num = parseInt(rows[i].roleNumber);
                console.log(num);
                if (isNaN(num)) {
                    $.messager.alert("系统提示", "请检查权限值，属于一个整数！");
                    return;
                }
            }
            var data = JSON.stringify(dataRows);
            console.log(data);
            $.ajax({
                url: "/saveRole",
                type: 'POST',
                dataType: 'json',
                data: {"data": data},
                success: function (response) {
                    console.log(response);
                    if (response.success) {
                        $.messager.alert("系统提示", "更新成功");
                        parent.$('#easy-side-tree').tree('reload');
                    } else {
                        $.messager.alert("系统提示", "更新失败");
                    }
                },
                error: function (xhr, status, error) {
                    console.log('请求发生错误: ' + error);
                }
            });
            editingId = undefined;
        }
    }
}

function cancel() {
    if (editingId != undefined) {
        $('#easy-editRole').datagrid('cancelEdit', editingId);
        editingId = undefined;
    }
}

function reject() {
    $('#easy-editRole').datagrid('cancelEdit', editingId);
    $('#easy-editRole').datagrid('reload');
    editingId = undefined;
}

function reloadCurGrid() {
    $('#easy-editRole').datagrid('reload');
}
