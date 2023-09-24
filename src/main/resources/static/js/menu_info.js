var editingId;

function edit(_field, _row) {
    if (editingId != undefined) {
        $('#easy-editMenu').treegrid('endEdit', editingId);
    }
    var row = $('#easy-editMenu').treegrid('getSelected');
    if (row) {
        editingId = row.id;
        $('#easy-editMenu').treegrid('beginEdit', editingId);

        if (_row.show_in_menu == 0 && _field == "name") {
            $('#easy-editMenu').treegrid('getEditor', {index: row.id, field: 'name'}).target.prop('readonly', true);
        }
    } else {
        $.messager.alert("系统提示", "请选择一条要编辑的数据！");
    }
}

function save() {
    var changeRows = $('#easy-editMenu').treegrid('getChanges');
    console.log("save()");
    if (changeRows) {
        if (editingId != undefined) {
            $('#easy-editMenu').treegrid('endEdit', editingId);
            var rows = $('#easy-editMenu').treegrid('getChildren');
            var dataRows = new Array();
            var integerEx = /^\d+$/;//check integer
            for (var i = 0; i < rows.length; i++) {
                dataRows[i] = {id: rows[i].id, name: rows[i].name, value: rows[i].value}
                if (!integerEx.test(rows[i].value)) {
                    $.messager.alert("系统提示", "请检查权限值，属于一个数字！");
                    return;
                }
            }
            $.ajax({
                url: "/saveMenu",
                type: 'POST',
                dataType: 'json',
                data: {"data": JSON.stringify(dataRows)},
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
        $('#easy-editMenu').treegrid('cancelEdit', editingId);
        editingId = undefined;
    }
}

function reject() {
    $('#easy-editMenu').treegrid('cancelEdit', editingId);
    $('#easy-editMenu').treegrid('reload');
    editingId = undefined;
}

function reloadCurGrid() {
    $('#easy-editMenu').treegrid('reload');
}