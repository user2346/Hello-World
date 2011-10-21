#!/usr/bin/perl

use strict;
use warnings;

sub factorial {
	my $x = shift;
	if ($x > 1) {
		my $total = $x * &factorial($x-1); #Keep multiplying until x = 1
	} else {
		return 1;
	}
}

sub power_of { #similar to factorial except the exp acts like a counter and base doesn't change
	my $base = shift;
	my $exp = shift;
	if ($exp > 0) {
		my $total = $base * &power_of($base, $exp-1); 
	} else {
		return 1;
	}
}

sub cap_first {
	my @allWords = ();
	foreach (@_) {
		my $word = $_;
		$word = lc ($word); #Comment to only change first letter
		my $firstLetter = substr $word, 0, 1;
		my $capLetter = uc ($firstLetter); #uppercase
		$word = &str_replace($firstLetter,$capLetter,$word);
		$word = "$word "; #Puts space after each word
		push(@allWords, $word);
	}
	return @allWords;
}

sub str_replace {
	my $replace_this = shift;
	my $with_this  = shift; 
	my $string   = shift;
	
	my $length = length($string);
	my $target = length($replace_this);
	
	for(my $i=0; $i<$length - $target + 1; $i++) {
		if(substr($string,$i,$target) eq $replace_this) {
			$string = substr($string,0,$i) . $with_this . substr($string,$i+$target);
			return $string; #Comment this if you what a global replace
		}
	}
	return $string;
}

sub all_chars {
	return ('a'..'z','A'..'Z','0'..'9','_','!','@',"#",'$','%','^','&','*','(',')','-','=','+','<','>',',','.','/','?',';',':','\'',"\"",'\\','|','[',']','{','}');
}

sub rand_string {
	my $word = "";
	my $length = shift;
	my @chars= &all_chars;
	my $rand;
	foreach (1..$length) {
		$rand = rand($#chars+1);
		$word = "$word$chars[$rand]";
	}
	return $word;
}

sub guess_word {
	my $to_guess = shift;
	my $guess = "";
	while ($guess ne $to_guess) {
		$guess = &rand_string(length($to_guess));
	}
	return $guess;
}

1;