# -*- coding: utf-8 -*-
#
# Skeleton file for the Python "Bob" exercise.
#
import re 

QUESTION = ".*[a-z1-9].*\?\s*$"
SILENCE = "^\s*$"

def hey(what):
    if re.search(QUESTION, what):
        return  "Sure."
    if re.search(SILENCE, what):
        return  "Fine. Be that way!"
    if what.isupper():
        return 'Whoa, chill out!'
    return "Whatever."
