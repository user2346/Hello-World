#!/usr/bin/perl -w

use strict;
use CGI qw/:standard/;
use warnings;
use user_data;
use session;
use print_stuff;
use file_manip;

if (!&get_session_id) {
    if ((param('pass') eq param('firmPass')) && (param('pass') ne '') && (!(&get_user('username'=>param('user'))))) {
	my $user = param('user');
	my $pass = param('pass');
	
	&add_user('username'=>$user,'password'=>$pass);

	my ($id,$name,$pass) = &get_user('username'=>$user);
	
	&set_session_id($id);

	print start_html(-title=>"Welcome $user",
			    -bgcolor=>'grey');
	&print_header($user);
	print h1("Welcome $user");
	print "<br/><br/><br/><br/><br/>";
	print "<a border='0' title='Home' href='index.pl'>Go back to Home</a>";
	print end_html;
    } else {
	print header;
	print start_html(-title=>"Sign Up",
			    -bgcolor=>'grey');
	&print_header;
	print h1("Sign Up");
	print start_form(-action => "sign_up.pl");
	print "Username:&nbsp;",
		textfield(
		    -name    => 'user',
		    -default => ''
		);
	print '<br/>';
	if (&get_user('username'=>param('user'))) {
	    print '<font color="red">Username is taken</font>';
	    print '<br/>';
	}
	print '<br/>';
	print "Password:&nbsp;",
		password_field(
		    -name    => 'pass',
		    -value => ''
		);
	print '<br/>';
	unless ((param('pass') eq param('firmPass')) || param('pass') eq '') {
	    print '<font color="red">Passwords don\'t match</font>';
	    print '<br/>';
	}
	print '<br/>';
	print "Confirm Password:&nbsp;",
		password_field(
		    -name    => 'firmPass',
		    -value => ''
		);
	print '<br/>';
	print '<br/>';
	print submit('Submit');
	print end_form;
	print end_html;
    }
} else {
    my $user = param('user');
    print header;
    print start_html(-title=>"Welcome $user",
			-bgcolor=>'grey');
    &print_header;
    print h1("Welcome $user");
    print "<br/><br/><br/><br/><br/>";
    print "<a border='0' title='Home' href='index.pl'>Go back to Home</a>";
    print end_html;
}