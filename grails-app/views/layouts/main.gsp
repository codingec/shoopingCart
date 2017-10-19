<!doctype html>
<html lang="en" class="no-js">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Shop</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${assetPath(src: 'favicon.png')}" type="image/x-icon">
    <asset:javascript src="jquery-2.2.0.min.js" />
    <asset:javascript src="bootstrap.js" />
    <asset:javascript src="public/itemsForSale.js" />
    <asset:javascript src="ecommerce/ecommerce.js" />
    <asset:stylesheet src="bootstrap.css" />
    <asset:stylesheet src="public/itemsForSale.css" />
    <asset:stylesheet src="ecommerce/ecommerce.css" />

    <asset:stylesheet src="form.css" />
    <asset:stylesheet src="mainPg.css" />
    <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
</head>
<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60">

<g:render template="/layouts/Public/topbar" />
<g:render template="/layouts/Public/slider" />
<g:render template="/layouts/Public/aboutSection" />
<g:render template="/layouts/Public/services" />
<g:render template="/layouts/Public/portafolio" />

<g:render template="/layouts/Public/pricing" />
<g:render template="/layouts/Public/contact" />
<g:render template="/layouts/Public/register" />
<g:render template="/layouts/Public/ecommerseDetails" />
<g:render template="/layouts/Public/itemsForSale" />

<g:render template="/layouts/Public/form" />
<g:render template="/layouts/Public/subscribeJumbotron" />
<g:render template="/layouts/Public/footer" />

<script>
    $(document).ready(function(){
        // Add smooth scrolling to all links in navbar + footer link
        $(".navbar a, footer a[href='#myPage']").on('click', function(event) {
            // Make sure this.hash has a value before overriding default behavior
            if (this.hash !== "") {
                // Prevent default anchor click behavior
                event.preventDefault();

                // Store hash
                var hash = this.hash;

                // Using jQuery's animate() method to add smooth page scroll
                // The optional number (900) specifies the number of milliseconds it takes to scroll to the specified area
                $('html, body').animate({
                    scrollTop: $(hash).offset().top
                }, 900, function(){

                    // Add hash (#) to URL when done scrolling (default click behavior)
                    window.location.hash = hash;
                });
            } // End if
        });

        $(window).scroll(function() {
            $(".slideanim").each(function(){
                var pos = $(this).offset().top;

                var winTop = $(window).scrollTop();
                if (pos < winTop + 600) {
                    $(this).addClass("slide");
                }
            });
        });
    })
</script>

</body>
</html>

