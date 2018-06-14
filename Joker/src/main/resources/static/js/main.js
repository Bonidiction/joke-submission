jQuery(document).ready(function($){
	$('button').click(function() {
		if ($(this).hasClass("glyphicon glyphicon-thumbs-up")) {
			$.post("/like", $(this).attr('id'), location.reload(), "text");
		} else if ($(this).hasClass("glyphicon glyphicon-thumbs-down")) {
			$.post("/dislike", $(this).attr('id'), location.reload(), "text");
		} else {
			alert("You f***** up!");
		}
	});
});