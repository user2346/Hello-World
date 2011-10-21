#!/usr/bin/perl -w

use strict;
use CGI qw/:standard/;
use warnings;
use user_data;
use session;
use print_stuff;
use file_manip;

print header;
print start_html(-title=>"Add to Thread",
		    -bgcolor=>'grey');
&print_header;
print h1("Add to Thread");
print "<a border='0' title='Back to Thread' href='main_thread.pl'>Back to Thread</a>";
print "<br/><br/>";
print start_form(-action => "added_thread.pl");
print "<table width=100%>";
print "<td align=\"center\">";
print textarea(-name => 'text',
		    -rows => "20",
		    -columns => "50");
print "</td>";
print "</table>";
print "<br/><br/>";
print submit('Submit Post');
print end_form;
print end_html;