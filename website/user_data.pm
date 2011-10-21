#!/usr/bin/perl -w

use strict;
use CGI qw/:standard/;
use warnings;
use session;
use print_stuff;
use file_manip;

sub add_user {
    my %args = @_;
    my $user = $args{username} || die "Username is not specified";
    my $pass = $args{password} || die "Password is not specified";
    
    my $lastKey = &last_user_id;
    my $thisKey = $lastKey + 1;
    my $my_file = "users.csv";
    &append_to_file($my_file,"$thisKey,$user,$pass,\n");
    #&makeHomePage;
    return 1;
}

sub last_user_id {
    my @data = &open_users;
    my $lastUser = $data[-1];
    my @values = split(/,/,$lastUser);
    my $lastKey = $values[0];
    return $lastKey;
}

sub get_user {
    my %args = @_;
    my $id = $args{id};# || die "Username is not specified";
    my $user = lc($args{username});# || die "Password is not specified";
    my $pass = $args{password};# || die "Username is not specified";

    my @data = &open_users;
    foreach (@data) {
	my @values = split(/,/,$_);
	if (($values[0] eq $id) || (lc($values[1]) eq $user) || ($values[2] eq $pass)) {
	    return @values;
	}
    }
    return 0;
}

sub does_password_match {
    my %args = @_;
    my $user = $args{username};# || die "Username is not specified";
    my $pass = $args{password};# || die "Password is not specified";
    my @data = &open_users;
    foreach (@data) {
	my @values = split(/,/,$_);
	if (($values[1] eq $user) && ($values[2] eq $pass)) {
	    return 1; #true
	}
    }
    return 0; #false
}

1;