#!/usr/bin/perl -w

use strict;
use CGI qw/:standard/;
use warnings;
use user_data;
use session;
use print_stuff;
use file_manip;

print header;
print start_html(-title=>"Forums",
		    -bgcolor=>'grey');
&print_header;
print "<table width=100%>";
print "<td>";
print h1("Forums Home");
print "</td>";
print "<td align='right'>";
print "<a border='0' title='Add a thread' href='main_thread.pl'>Add a thread</a>";
print "</td>";
print "</table>";
print "<a border='0' title='Main Thread' href='main_thread.pl'>Main Thread</a>";
print end_html;