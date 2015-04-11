#!/usr/bin/env python

def hey(in_string):
    if in_string.isupper():
        return u'Whoa, chill out!'
    if in_string[-1:] == u'?':
        return u'Sure.'
    elif in_string == u'' or in_string.isspace():
        return u'Fine. Be that way!'
    else:
        return u'Whatever.'
