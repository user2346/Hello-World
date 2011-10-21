#!/usr/bin/perl -w

use strict;
use CGI qw/:standard/;
use warnings;
use user_data;
use session;
use print_stuff;
use file_manip;

print header;
my @data = &open_file("main_thread_data.csv");
my @thread_info = split(/,/,$data[0]);
print start_html(-title=>$thread_info[0],
		    -bgcolor=>'grey');
&print_header;
my ($id,$name,$pass) = &get_user('id'=>$thread_info[1]);
print "<table>";
print "<td>";
print h1($thread_info[0]);
print "</td>";
print "<td>";
print "by $name";
print "</td>";
print "</table>";
if (&get_session_id) {
    print "<a border='0' title='Add to thread' href='add_thread.pl'>Add to this thread</a>";
}
print "<br/><br/>";
print "<table border=1 width=100%>";
shift(@data);
foreach (@data) {
    print "<tr>";
    print "<td width='20%'>";
    my @post = split(/,/,$_);
    my ($id,$name,$pass) = &get_user('id'=>$post[0]);
    print $name;
    print "</td>";
    print "<td width='80%'>";
    my @file = &open_file($post[1]);
    foreach (@file) {
		$_ =~ tr/\015//d;
		print;
    }
    if ($id==&get_session_id) {
	print "<div align='right'><a border='0' title='Edit' href='edit_thread.pl'>Edit</a></div>";
    }
    print "</td>";
    print "</tr>";
}
print "</table>";
print end_html;
