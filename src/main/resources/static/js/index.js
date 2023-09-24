
$(document).ready(function() {
    $('#easy-side-tree').tree({
        url: '/loadMenu', // 菜单数据接口
        method: 'get', // 请求方法，根据实际情况修改为 'get' 或 'post'
        animate: true, // 是否显示动画效果
        onLoadSuccess: function (node, data) {
            // 菜单数据加载成功后的回调函数
            // 可以在这里对菜单数据进行处理或其他操作
        },
        onClick: function (node) {
            // 菜单节点点击事件处理函数
            var url = node.attributes['url'];
            if (url == null || url === "") {
                return false;
            } else {
                addTab(node.text, url, node.iconCls, node.attributes['iframe']);
            }
        }
    });


    $('#easy-tabs').tabs({
        tools: [{
            iconCls: 'icon-reload',
            border: false,
            handler: function () {
                if (getTabWindow() != null && getTabWindow().reloadCurGrid) {
                    getTabWindow().reloadCurGrid();
                }
            }
        }]
    });

    function getTabWindow() {
        var curTabWin = null;
        var curTab = parent.$('#easy-tabs').tabs('getSelected');
        if (curTab && curTab.find('iframe').length > 0) {
            curTabWin = curTab.find('iframe')[0].contentWindow;
        }
        return curTabWin;
    }

    function addTab(title, href, iconCls, iframe) {
        console.log(href);
        var tabPanel = $('#easy-tabs');
        if (!tabPanel.tabs('exists', title)) {
            var content = '<iframe scrolling="auto" frameborder="0"  src="' + href + '" style="width:100%;height:100%;"></iframe>';
            console.log(content);
            if (iframe) {
                tabPanel.tabs('add', {
                    title: title,
                    content: content,
                    iconCls: iconCls,
                    fit: true,
                    cls: 'pd3',
                    closable: true
                });
            } else {
                tabPanel.tabs('add', {
                    title: title,
                    href: href,
                    iconCls: iconCls,
                    fit: true,
                    cls: 'pd3',
                    closable: true
                });
            }
        } else {
            tabPanel.tabs('select', title);
        }
    }

    function removeTab() {
        var tabPanel = $('#easy-tabs');
        var tab = tabPanel.tabs('getSelected');
        if (tab) {
            var index = tabPanel.tabs('getTabIndex', tab);
            tabPanel.tabs('close', index);
        }
    }

    getUser();

});


function logout() {
    $.ajax({
        url: '/logout',
        type: 'GET',
        dataType: 'json',
        success: function() {
            // 重定向到登录页面
        }
    });
    sessionStorage.clear();
    window.location.href = '/pages/login.html';
}

function getUser(){
    $.ajax({
        url: '/getCurrentUser',
        type: 'GET',
        dataType: 'json',
        success: function(response) {
            console.log(response);
            var sessionDataElement = document.getElementById('username');
            sessionDataElement.innerHTML = '当前用户：' + response.trueName;
        },
        error: function(xhr, status, error) {
            console.log('AJAX请求失败：' + error);
        }
    });
}
