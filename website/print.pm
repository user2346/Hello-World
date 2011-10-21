#!/usr/bin/perl

use strict;
use CGI qw/:standard/;
use warnings;
use UserData;

sub print_header {
    print "<table height='28' border=0>
	    <tr>
	    <td style=\"width:200px\">Welcome ";

    if (&getSessionID) {
	print &getUserName(&getSessionID); 
    } else {
	print "Guest";
    }

    print "</td>
	    <td style=\"width:1100px\" align=\"center\">
	    <b><font size=\"5\">My Website</font></b>
	    </td>
	    <td style=\"width:90px\">
	    <a border='0' title='Home' href='Index.pl'>Home</a>";
    print "</td>";
    print "<td style='width:100px'>";

    if (&getSessionID) {
	print "<a border='0' title='Log Out' href='LogOut.pl'>Log Out</a>";
    } else { 
	print "<a border='0' title='Sign up' href='SignUp.pl'>Sign Up</a>";
	print "</td>";
	print "<td style='width:100px'>";
	print "<a border='0' title='Log in' href='LogIn.pl'>Log In</a>";
    }

    print "</td>
	    </tr>
	    </table>";
    print hr;
}

1;