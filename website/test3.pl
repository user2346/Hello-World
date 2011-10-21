#!/usr/bin/perl -w

use CGI qw/:standard/;
use strict

print
    header,
    start_html('Simple Script'),
    h1('Simple Script'),
    start_form,
    "What's your name? ",textfield('name'),p,
    "What's the combination?",
    checkbox_group(-name=>'words',
	-values=>['eenie','meenie','minie','moe'],
	-defaults=>['eenie','moe']),p,
    "What's your favorite color?",
    popup_menu(-name=>'color',
	-values=>['red','green','blue','chartreuse']),p,
    submit,
    end_form,
    hr,"\n";

if (param('name') ne '') {
    print
    "Your name is ",em(param('name')),p,
    "The keywords are: ",em(join(", ",param('words'))),p,
    "Your favorite color is ",em(param('color')),".\n";
}
print end_html;