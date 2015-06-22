var current_page    =   1;
var loading         =   false;
var oldscroll       =   0;

$('document').ready(function(){
	
	
	$.get( "rest/details", function( data ) {
		for ( var i=0; i < data.boards.length ; i++){
			var obj = data.boards[i];

			$("#columns").append("<div id =\"pin"+i+"1\" class=\"pin\">");
			$("#pin"+i+"1").append("<p style=\"text-align:left\">"+ obj.title+"</p><p style=\"text-align:right\">@"+obj.author+"</p>");
			$("#pin"+i+"1").append("<img src=\""+obj.thumbnail_url+"\" />");
			$("#pin"+i+"1").append("<p>"+obj.blurb+"</p>");
			$("#pin"+i+"1").append("<a href=\"#\">"+obj.details_url+"</a>");		
		}
		current_page++;
		});

	
    morepages=true;
	   $(window).scroll(function() {		  
	        if( $(window).scrollTop() > oldscroll ){ //if we are scrolling down
	        	
		        var wintop = $(window).scrollTop(), docheight = $(document).height(), winheight = $(window).height();
		        var  scrolltrigger = 0.95;
	
		        if(morepages){
		        if  ((wintop/(docheight-winheight)) >scrolltrigger) {
	                        $('#columns').addClass('loading');
	                   //     sleep(2000);
	                    //    console.log(current_page);
	                    	$.get( "rest/details/"+current_page+"/10", function( data ) {
	                    
	                    		for ( var i=0; i < data.boards.length ; i++){
	                    			var obj = data.boards[i];
	                    			$("#columns").append("<div id =\"pin"+i+current_page+"\" class=\"pin\">");
	                    			$("#pin"+i+current_page).append("<p style=\"text-align:left\">"+ obj.title+"</p><p style=\"text-align:right\">@"+obj.author+"</p>");
	                    			$("#pin"+i+current_page).append("<img src=\""+obj.thumbnail_url+"\" />");
	                    			$("#pin"+i+current_page).append("<p>"+obj.blurb+"</p>");
	                    			$("#pin"+i+current_page).append("<a href=\"#\">"+obj.details_url+"</a>");                    			
	                    		}
	                    		morepages=data.morePages;
		                    	if(morepages==true){
			                    current_page++;
		                    	}else{
		                    		   $('#columns').addClass('loading');
		                    		   sleep(5000);
		                    		   $('#columns').removeClass('loading'); 
		                        		$("#wrapper").append("<div style=\"text-align:center\"> <pre>No more contents to show</pre></div>");
		                       	}
		                    	$('#columns').removeClass('loading'); 
	                    		});
	                    	

	            }
	        }
	        }
	    });
	   
	   function sleep(milliseconds) {
		   var start = new Date().getTime();
		   for (var i = 0; i < 1e7; i++) {
		     if ((new Date().getTime() - start) > milliseconds){
		       break;
		     }
		   }
		 }
});