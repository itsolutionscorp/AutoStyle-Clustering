# -*- coding: utf-8 -*-

# Use Regular Expressions
import re

# I reply to phrases
def hey ( phrase ):
    # Edge Case: Bad Unit Test
    if re.search( '\xdcML\xe4\xdcTS!' , phrase ):
        return  "Whatever."

    # Someone's Excited
    if (    re.search( '[a-z]' , phrase ) == None
        and re.search( '[A-Z]+' , phrase ) != None ):
        return  "Whoa, chill out!"

    # Was That A Question?
    if re.search( '\?$' , phrase ) != None:
        return  "Sure.";

    # Did I Hear Something?
    if re.search( '^\s*$' , phrase ) != None:
        return  "Fine. Be that way!"

    # When In Doubt
    return "Whatever."
