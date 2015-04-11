# -*- coding: utf-8 -*-

# Use Regular Expressions
import re

# I reply to phrases
def hey ( phrase ):
    # Someone's Excited
    if phrase.isupper():
        return  "Whoa, chill out!"

    # Was That A Question?
    if re.search( '\?$' , phrase ):
        return  "Sure.";

    # Did I Hear Something?
    if re.search( '^\s*$' , phrase ):
        return  "Fine. Be that way!"

    # When In Doubt
    return "Whatever."
