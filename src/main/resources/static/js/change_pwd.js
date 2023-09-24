
if ($.messager) {
    $.messager.defaults.ok = '确定';
    $.messager.defaults.cancel = '取消';
}

function changepwd() {
    var old_password = $('#user_old_password').val().toString();
    var new_password = $('#user_new_password').val().toString();
    var confirm_new_password = $('#user_confirm_new_password').val().toString();

    if (old_password.length > 0) {
        if (new_password.length > 0) {
            if (new_password == confirm_new_password) {
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "/changePassword",
                    data: $('#change_password').serialize(),
                    success: function (response) {
                        console.log(response);
                        $.messager.alert("系统提示", response.message);
                    },
                    error: function () {
                        $.messager.alert('系统提示','未知异常！');
                    }
                });
            }
            else {
                $.messager.alert('系统提示', "两次密码输入不相同！");
            }
        }
        else {
            $.messager.alert('系统提示', "请输入新密码！");
        }
    }
    else {
        $.messager.alert('系统提示', "请输入旧密码！");
    }
}