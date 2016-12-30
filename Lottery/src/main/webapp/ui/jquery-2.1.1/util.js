var appId = window.parent.appId;
var protocol = "http://";
var context = "/";
var uri = getUri();
var prefix = protocol + uri + context;
function getUri() {
	var uri = location.host;
	return uri;
}

function getParams(key) {
	var url = window.location.search.replace(/^\?/, '').split('&');
	var paramsObj = {};
	for (var i = 0, iLen = url.length; i < iLen; i++) {
		var param = url[i].split('=');
		paramsObj[param[0]] = param[1];
	}
	if (key) {
		return paramsObj[key] || '';
	}
	return paramsObj;
}