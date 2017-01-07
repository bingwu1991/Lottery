function submit() {
	var first = $('#first').val();
	var second = $('#second').val();
	var last = $('#last').val();
	var four = $('#four').val();
	var five = $('#five').val();
	var count = $('#count').val();
	var type = $('#type').val();
	var startPeriod = $('#startPeriod').val();
	var endPeriod = $('#endPeriod').val();
	var data = 'count=' + count + '&startPeriod=' + startPeriod + '&endPeriod='
			+ endPeriod + '&first=' + first + '&second=' + second + '&last='
			+ last + '&four=' + four + '&five=' + five + '&type=' + type;
	$('li').remove();

	$.ajax({
		type : "POST",
		contentType : 'application/x-www-form-urlencoded',
		url : prefix + "/lottery/statistic",
		data : data,
		success : function(data) {
			var dataObj = eval(data);
			parseJson(dataObj);
		}
	});
}
function parseJson(data, grandparent, parent) {
	var length = Object.keys(data).length;
	var type = $('#type').val();
	$.each(data, function(name, value) {
		if ('grandFirst' == name || 'grandSecond' == name
				|| 'grandLast' == name) {
			parseJson(value, name);
		} else if ('grandFour' == name || 'grandFive' == name) {
			if (type == 1) {
				parseJson(value, name);
			}
		} else {
			if ('first' == name || 'second' == name || 'last' == name) {
				parseJson(value, grandparent, name);
			} else if ('four' == name || 'five' == name) {
				if (type == 1) {
					parseJson(value, grandparent, name);
				}
			} else {
				$('#' + grandparent + '_' + parent).append(
						'<li>' + name.substr(1) + ' -> ' + value + '</li>')
			}
		}
	});
}
