#!/usr/bin/perl -w

use strict;
use CGI qw/:standard/;

sub print_form {
    print start_form;
    my $person = shift;

    print "Please enter your name: ";
    if ($person) {

        print 
	   textfield(
            -name    => 'user',
            -default => $person
          ),
          p;
    } else {
        print
          textfield(
            -name    => 'user',
            -default => ''
          ),
          hr;
    }

    print "Type in the password: ",
      password_field(
        -name      => 'pass',
        -default   => '',
        -override  => 1,
        -size      => 6,
        -maxlength => 6
      ),
      p end_form;
}

my $person = "";

if ( param() && ( $person = param('user') ) ) {
    my $user_cookie = cookie(
        -name    => 'user',
        -value   => $person,
        -expires => "+30d"
    );
    print header( -cookie => $user_cookie );
} else {
    print header;
    $person = cookie("user");
}

print
  start_html('Test'),
  h1("Test"),
  hr;
print_form($person);
print hr;

if ( param() ) {
    my $message = "";
    my $pass    = param('pass');
    if ( $pass && ( length($pass) == 6 ) ) {
        $message = "password length correct (6 chars)";
    } else {
        $message = "password missing or not valid.";
    }
    print $message, p;
    print hr;
}

print end_html;

