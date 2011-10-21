#!/usr/bin/perl -w

use strict;
use CGI qw/:standard/;
use warnings;
use user_data;
use session;
use print_stuff;
use file_manip;

print header;
my $message = param('text');
my $id = &get_session_id;
my $count = 1;
while (-e "main_thread_post$count.txt") {
    $count++;
}
$message =~ tr/\015//d;
open (FILE, ">main_thread_post$count.txt");
    print FILE $message;
close FILE;
chmod (0666, "main_thread_post$count.txt");
&append_to_file("main_thread_data.csv","$id,main_thread_post$count.txt,\n");
print start_html(-title=>"Added",
		    -bgcolor=>'grey');
&print_header;
print h1("Added");
print "<a border='0' title='Back to Thread' href='main_thread.pl'>Back to Thread</a>";
print end_html;