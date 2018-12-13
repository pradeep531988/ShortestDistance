$(document).ready(function(){
	
	$('#allDistanceFromSrc').click(function(){
		$('#distBetweenSrcDest').removeClass('active');
		$('#allDistanceFromSrc').addClass('active');
		$('#content1').show();
		$('#content2').hide();
	}
	);

	$('#distBetweenSrcDest').click(function(){
		$('#distBetweenSrcDest').addClass('active');
		$('#allDistanceFromSrc').removeClass('active');
		$('#content2').show();
		$('#content1').hide();
	}
	);
	
	$('#reCalculateDistance').trigger('change');
	
	$('#reCalculateDistance').change(function (){
		const tables = $.fn.dataTable.fnTables(true);

		$(tables).each(function () {
		  $(this).dataTable().fnClearTable();
		  $(this).dataTable().fnDestroy();
		});
		
        const selectedIndex = $("#reCalculateDistance option:selected")[0].label;
        $.getJSON('/transport/src/'+selectedIndex, function(response) {
        	
        	$('#dataTableOne').dataTable({
        		 "aaData": response.graphResponseList.map(function (r) {
                     return [
                         r.srcNode,
                         r.destNode,
                         r.shortestPathRoute,
                         r.weight,
                     ];
                 }),
                 "sScrollY": "200px",
                 "bScrollCollapse": true,
                 "bPaginate": false,
                 "bJQueryUI": true,
                 "aoColumnDefs": [
                     { "sWidth": "10%", "aTargets": [ -1 ] }
                 ]
        	});
        	
        	$('#dataTableOne').addClass('fullWidth');
         });	
	});
	
	$('#srcNodeValue, #destNodeValue').change(function (){
		const selectedIndex = $("#srcNodeValue option:selected")[0].label;
		const destNode = $("#destNodeValue option:selected")[0].label;

        $.getJSON('/transport/src/'+selectedIndex+'/dest/'+destNode, function(response) {
        	console.log(response);
        	$('#path').val(response.shortestPathRoute);
        	$('#weight').val(response.weight);
        });
	});
});