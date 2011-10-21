#!/usr/bin/perl -w

use strict;
use CGI qw/:standard/;
use warnings;
use user_data;
use session;
use print_stuff;
use file_manip;

if (&does_password_match('username'=>param('user'),'password'=>param('pass'))) {
    my $user = param('user');
    my ($id,$name,$pass) = &get_user('username'=>$user);
    
    &set_session_id($id);

    print start_html(-title=>"Welcome back, $user",
			-bgcolor=>'grey');
    &print_header($user);
    print h1("Welcome back, $user");
    print "<br/><br/><br/><br/><br/>";
    print "<a border='0' title='Home' href='index.pl'>Go back to Home</a>";
    print end_html;
} else {
    print header;
    print start_html(-title=>"Log In",
			-bgcolor=>'grey');   
    &print_header;
    print h1("Log In");
    print start_form(-action => "log_in.pl");
    print "Username:&nbsp;",
	    textfield(
		-name    => 'user',
		-default => ''
	    );
    print '<br/>';
    print '<br/>';
    print "Password:&nbsp;",
	    password_field(
		-name    => 'pass',
		-value => ''
	    );
    print '<br/>';
    if ((param('user') ne '') || (param('pass') ne '')) {
	print '<font color="red">Username or Password is invalid</font>';
	print '<br/>';
    }
    print '<br/>';
    print submit('Log In');
    print end_form;
    print end_html;
}