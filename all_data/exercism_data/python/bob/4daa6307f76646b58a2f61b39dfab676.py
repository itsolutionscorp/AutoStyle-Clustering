# -*- coding: utf-8 -*-
#
# Skeleton file for the Python "Bob" exercise.
#

from __future__ import unicode_literals

ending = ["?","!"]

def hey(what):
    new_text = what.strip()
    
    if new_text == "":
        return "Fine. Be that way!"
    
    if new_text[-1] in ending:
        no_end = new_text[:-1]
    else:
        no_end = new_text
    
    if new_text == new_text.upper() and new_text[-1] not in ending and not no_end[-1].isdigit():
        return "Whoa, chill out!"
    
    
    if new_text.endswith("?"):
        if new_text == new_text.upper():
            if no_end.isdigit():
                return "Sure."
            else:
                return "Whoa, chill out!"
        else:
            return "Sure."
        
    if new_text.endswith("!"):
        if new_text == new_text.upper():
            return "Whoa, chill out!"
        else:
            return "Whatever."
    return "Whatever."
        
