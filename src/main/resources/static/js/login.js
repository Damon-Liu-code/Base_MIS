function login(){
    var requestData = {
        userName: document.getElementById("userName").value,
        password: document.getElementById("password").value
    };

    $.ajax({
        url: '/login', // 登录验证的后端接口地址
        type: 'POST',
        dataType: 'json',
        data: requestData,
        success: function(response) {
            console.log(response);
            if (response.success) {
                // 登录成功，重定向到主页
                window.location.href = 'index.html';
            } else {
                // 登录失败，显示错误消息
                alert(response.message);
            }
        },
        error: function(xhr, status, error) {
            console.log('登录请求发生错误: ' + error);
        }
    });
}
