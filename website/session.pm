#!/usr/bin/perl -w

use strict;
use CGI qw/:standard/;
use warnings;
use user_data;
use print_stuff;
use file_manip;

sub set_session_id {
    my $id = shift;
    my $cookie =
	    cookie(-name=>'session_id',
		-value=>$id,
		-secure=>0);
    print header(-cookie=>$cookie);
} 

sub get_session_id {
    my $query = new CGI;
    my $cookie = $query->cookie('session_id');
}

sub sign_out {
    my $cookie =
	    cookie(-name=>'session_id',
		-value=>'',
		-expire=>'-1d',
		-path=>'/',
		-secure=>0);
    print header(-cookie=>$cookie);
}

1;