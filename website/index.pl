#!/usr/bin/perl -w

use strict;
use CGI qw/:standard/;
use warnings;
use user_data;
use session;
use print_stuff;
use file_manip;

print header;
print start_html(-title=>"My website",
		    -bgcolor=>'grey');
&print_header;
print h1("My Home Page");
print end_html;