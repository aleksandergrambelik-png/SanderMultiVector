#!/usr/bin/env perl
use strict;
use warnings;
my $input = <STDIN>;
chomp($input);
my @chars = split //, $input;
my $encoded = "";
foreach my $char (@chars) {
    if ($char =~ /[a-zA-Z0-9]/) { $encoded .= $char; } 
    else { $encoded .= sprintf("%%%02X", ord($char)); }
}
print $encoded . "\n";
