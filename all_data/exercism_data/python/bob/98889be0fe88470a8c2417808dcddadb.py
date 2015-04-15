#-------------------------------------------------------------------------------
# Name:        module1
# Purpose:
#
# Author:      Mathman
#
# Created:     27/09/2014
# Copyright:   (c) Mathman 2014
# Licence:     <your licence>
#-------------------------------------------------------------------------------
#!/usr/bin/env python

def hey(x):
    if x.isupper():
        return "Whoa, chill out!"
    elif x.endswith ('?'):
        return "Sure."
    elif (x.isspace()) or (x==''):
        return "Fine. Be that way!"
    return "Whatever."
