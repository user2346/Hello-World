#!/usr/bin/perl -w

use strict;
use CGI qw/:standard/;
use warnings;
use user_data;
use session;
use print_stuff;
use file_manip;

&sign_out;

print start_html(-title=>"Log Out",
		    -bgcolor=>'grey');
&print_header('',1);
print h1("Logged Out");
print "<br/><br/><br/><br/><br/>";
print "<a border='0' title='Home' href='index.pl'>Go back to Home</a>";
print end_html;