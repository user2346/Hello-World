#!/usr/bin/perl -w

use strict;
use CGI qw(:standard);

print header(), start_html("Browser Detective");
print h1("Browser Detective"), hr();
my $browser = $ENV{'HTTP_USER_AGENT'};
$_ = $browser;

BROWER: {
	if (/msie/i) {
		msie($_);
	} elsif (/mozilla/i) {
		netscape($_);
	} elsif (/lynx/i) {
		lynx($_);
#	} elsif (/safari/i) {
#		safari($_);
	} else {
		default($_);
	}
}
print end_html();
sub netscape {
	print p("Netscape: @_. Good Choice\n");
}

sub lynx {
	print p("Lynx: @_. OKAY...\n");
}

sub msie {
	print p("Internet Explorer: @_. Umm...\nNo Comment\n");
}

sub netscape {
	print p("What the heck is  @_?");
}
