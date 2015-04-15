# -*- coding: utf-8 -*-

#
# Skeleton file for the Python "Bob" exercise.
#
import re

def hey(what):
    if re.match('^\s*$', what) is not None:
    	return "Fine. Be that way!"
    
    if what.isupper():
    	return "Whoa, chill out!"    

    if what.endswith('?'):
    	return "Sure."    

    return "Whatever."
