# -*- coding: utf-8 -*-
#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    
    what=what.strip(" ")
    
    if what.isupper() :
        reply = "Whoa, chill out!"
    elif what.endswith("?"):
        reply = "Sure."
    elif what == "" or what == "\t":
        reply = "Fine. Be that way!"
    else: 
        reply = "Whatever."
    
    return reply