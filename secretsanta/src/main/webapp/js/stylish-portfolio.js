
(function($) {
  "use strict"; // Start of use strict
function Group(group, users){
    this.groupName = group;
    this.users = users;
}
function User(user, email){
    this.userName = user;
    this.userEmail = email;
}

  // Closes the sidebar menu
  $("#menu-close").click(function(e) {
    e.preventDefault();
    $("#sidebar-wrapper").toggleClass("active");
  });

  // Opens the sidebar menu
  $("#menu-toggle").click(function(e) {
    e.preventDefault();
    $("#sidebar-wrapper").toggleClass("active");
  });

  // Smooth scrolling using jQuery easing
  $('a.js-scroll-trigger[href*="#"]:not([href="#"])').click(function() {
    if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
      var target = $(this.hash);
      target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
      if (target.length) {
        $('html, body').animate({
          scrollTop: target.offset().top
        }, 1000, "easeInOutExpo");
        return false;
      }
    }
  });

//adding a person button
  $(document).ready(function(){
      var next = 1;
      $(".add-more").click(function(e){
          e.preventDefault();
          var addto = "#field" + next;
          var addRemove = "#field" + (next);
          next = next + 1;
          //var newIn = '<input autocomplete="off" class="input form-control" id="field' + next + '" name="field' + next + '" type="text">';
          var newIn = `<div id="field${next}" name="field${next}" class="form-group floating-label-form-group controls buttonstyle">
            <input class="form-control" id="name" type="text" placeholder="Name" required data-validation-required-message="Please enter your name.">
            <input class="form-control" id="email" type="email" placeholder="Email Address" required data-validation-required-message="Please enter your email address."></div>`
          var newInput = $(newIn);
          var removeBtn = '<button id="remove' + (next - 1) + '" class="btn btn-danger remove-me" >-</button></div><div id="field">';
          var removeButton = $(removeBtn);
          $(addto).after(newInput);
          $(addRemove).after(removeButton);
          $("#field" + next).attr('data-source',$(addto).attr('data-source'));
          $("#count").val(next);

              $('.remove-me').click(function(e){
                  e.preventDefault();
                  var fieldNum = this.id.charAt(this.id.length-1);
                  var fieldID = "#field" + fieldNum;
                  $(this).remove();
                  $(fieldID).remove();
              });
      });

    //iterates through our people thing
        function submit(){
            document.getElementByID(contactForm);
            
        }

  // Closes responsive menu when a scroll trigger link is clicked
  $('.js-scroll-trigger').click(function() {
    $("#sidebar-wrapper").removeClass("active");
  });

  //#to-top button appears after scrolling
  var fixed = false;
  $(document).scroll(function() {
    if ($(this).scrollTop() > 250) {
      if (!fixed) {
        fixed = true;
        $('#to-top').show("slow", function() {
          $('#to-top').css({
            position: 'fixed',
            display: 'block'
          });
        });
      }
    } else {
      if (fixed) {
        fixed = false;
        $('#to-top').hide("slow", function() {
          $('#to-top').css({
            display: 'none'
          });
        });
      }
    }
  });

})
}) (jQuery); // End of use strict
