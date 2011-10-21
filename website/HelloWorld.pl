#!/usr/bin/perl -w
use CGI; # load CGI routines
$q = new CGI; # create new CGI object
print $q->header, # create the HTTP header
$q->start_html('hello world'), # start the HTML
$q->h1('hello world'), # level 1 header
$q->end_html; # end the HTML
