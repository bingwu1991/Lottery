function submit() {
	var first = $('#first').val();
	var second = $('#second').val();
	var last = $('#last').val();
	var four = $('#four').val();
	var five = $('#five').val();
	var type = $('#type').val();
	var period = $('#period').val();
	var data = 'type=' + type + '&period=' + period + '&first=' + first
			+ '&second=' + second + '&last=' + last + '&four=' + four
			+ '&five=' + five;
	var nextPeriod = Number(period) + 1;
	$.ajax({
		type : "POST",
		contentType : 'application/x-www-form-urlencoded',
		url : prefix + "/lottery/addPeriod",
		data : data,
		success : function(data) {
			$('#first').val('');
			$('#second').val('');
			$('#last').val('');
			$('#four').val('');
			$('#five').val('');
			$('#period').val(nextPeriod);
		}
	});
}

function del() {
	var period = $('#period').val();
	var type = $('#type').val();
	$.ajax({
		type : "POST",
		contentType : 'application/x-www-form-urlencoded',
		url : prefix + "/lottery/delete",
		data : 'period=' + period + '&type=' + type,
		success : function(data) {
			query();
		}
	});

}

function query() {
	$('tr:not(#title)').remove();

	var startPeriod = $('#startPeriod').val();
	var endPeriod = $('#endPeriod').val();
	var type = $('#type').val();
	$.ajax({
		type : "get",
		url : prefix + "/lottery/query?startPeriod=" + startPeriod
				+ "&endPeriod=" + endPeriod + "&type=" + type,
		success : function(data) {
			var dataObj = eval(data);
			$.each(data, function(index, lottery) {
				var append = '<tr id="content">'
				$.each(lottery, function(key, value) {
					if (key != 'type') {
						if (value == null) {
							value = '';
						}
						append = append + '<td>' + value + '</td>';
					}
				})
				append = append + '</tr>';
				$('#data').append(append);
			})
		}
	});
}
