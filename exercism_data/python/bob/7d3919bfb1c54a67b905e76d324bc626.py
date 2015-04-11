#!/usr/bin/python
# -*- coding: utf-8 -*-
import re
import unicodedata
#
# Skeleton file for the Python "Bob" exercise.
#
CHILLOUT = 'Whoa, chill out!'
ASSERT = 'Sure.'
GO_ON = 'Fine. Be that way!'
WHATEVER = 'Whatever.'

def hey(what):
    ascii_string = normalize(what)

    shout = re.compile('(\W|\d|\s|\t|\n|\r)*([A-Z]+(\W|\d|\s|\t|\n|\r)*)+')
    didShouted = shout.match(ascii_string)

    ask = re.compile('([A-Za-z0-9](\W|\d|\s|\t|\n|\r)*)*\?(\W|\d|\s|\t|\n|\r)*')
    didAsked = ask.match(ascii_string)

    nothing = re.compile('(\W|\s|\t|\n|\r)*')
    didNothing = nothing.match(ascii_string)

    
    if didShouted:
        if(u"".join(didShouted.group(0)) == normalize(ascii_string)):
            return CHILLOUT
    if didAsked:
        if(u"".join(didAsked.group(0)) == normalize(ascii_string)):
                return ASSERT
    if didNothing:
        if(u"".join(didNothing.group(0)) == normalize(ascii_string)):
                return GO_ON
    return WHATEVER
        
#normalize utf-8 chars
def normalize(input_str):
    ascii_string = u"".join(input_str)
    nkfd_form = unicodedata.normalize('NFKD', ascii_string)
    return u"".join([c for c in nkfd_form if not unicodedata.combining(c)])
