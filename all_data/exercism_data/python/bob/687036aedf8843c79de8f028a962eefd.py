#
# Skeleton file for the Python "Bob" exercise.
#
# -*- coding: utf-8 -*-
import string
import re

pattern = re.compile('[\w]*$')

def hey(what):
    if '' == what.strip():     # if the whole thing is blank
        return 'Fine. Be that way!'
    elif  what == what.upper() and re.search('[A-Z]', what):
        # if we're yelling (upper case letters everywhere)
        return 'Whoa, chill out!'
    elif '?' == what.strip()[-1]: # if its a question (and not yelling)
        return 'Sure.'
    else: # everything else.
        return 'Whatever.'
    return
