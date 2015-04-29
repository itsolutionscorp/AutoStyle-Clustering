# -*- coding: utf-8 -*-

from __future__ import unicode_literals

def hey(input):
    sentence = unicode(input)

    if len(sentence) == 0 or sentence.isspace():
        output = 'Fine. Be that way!'
    elif sentence.isupper():
        output = 'Whoa, chill out!'
    elif sentence[-1] == '?':
        output = 'Sure.'
    else:
        output = 'Whatever.'

    return output
