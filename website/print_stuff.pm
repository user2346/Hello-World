#!/usr/bin/perl -w

use strict;
use CGI qw/:standard/;
use warnings;
use user_data;
use session;
use file_manip;

# I think this print the main header...

sub print_header {
    my $user = shift;
    my $log_out = shift;
    print "<table height='28' border=0>
	    <tr>
	    <td style=\"width:200px\">Welcome ";
    if ($user && (!$log_out)) {
	print $user;
    } elsif (&get_session_id && (!$log_out)) {
	my ($id,$name,$pass) = &get_user('id'=>&get_session_id); 
	print $name;
    } else {
	print "Guest";
    }

    print "</td>
	    <td style=\"width:1100px\" align=\"center\">
	    <b><font size=\"5\">My Website</font></b>
	    </td>
	    <td style=\"width:90px\">";
    print "</td>
	    </tr>
	    </table>";
    print hr;
    print "<table width=\"100%\" frame='vsides' border=1>
	    <tr>
	    <td align=\"center\">
	    <a border='0' title='Home' href='index.pl'>Home</a>
	    </td>
	    <td align=\"center\">
	    <a border='0' title='Forums' href='forums_home.pl'>Forums</a>
	    </td>";
    print "<td align=\"center\" style='width:100px'>";

    if ((&get_session_id || $user) && (!$log_out)) {
	print "<a border='0' title='Log Out' href='log_out.pl'>Log Out</a>";
    } else { 
	print "<a border='0' title='Sign up' href='sign_up.pl'>Sign Up</a>";
	print "</td align=\"center\">";
	print "<td align=\"center\" style='width:100px'>";
	print "<a border='0' title='Log In' href='log_in.pl'>Log In</a>";
	#print button(-value=>"Log In",
	#		-onClick=>"parent.location='log_in.pl'");
    }

    print "</td>
	    </tr>
	    </table>";
    print hr;
}

1;