#!/usr/bin/perl -w

use strict;
use CGI qw/:standard/;
use warnings;
use user_data;
use session;
use print_stuff;

sub open_file {
    my $my_file = shift;
    open FILE, "<",$my_file || die("This file will not open!");
    my @data = <FILE>;
    close(FILE);
    return @data;
}

sub open_users {
    my $my_file = 'users.csv';
    open FILE, "<",$my_file || die("This file will not open!");
    my @data = <FILE>;
    close(FILE);
    return @data;
}

sub append_to_file {
    my $my_file = shift;
    my $to_print = shift;
    open DAT, ">>", $my_file || die("This file will not open!");
    print DAT $to_print;
    close(DAT);
    return 1;
}

sub make_home_page {
    my $id = shift;
    my $cookie =
	    cookie(-name=>'sessionID',
		-value=>$id,
		-secure=>0);
    print header(-cookie=>$cookie);
}

1;