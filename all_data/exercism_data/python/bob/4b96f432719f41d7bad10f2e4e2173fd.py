# -*- coding: utf-8 -*-

from __future__ import unicode_literals
import re


def hey(input):
    # initialize variables
    reply = ''
    
    # if whitespace, consider it not saying anything
    if re.match('^\s*$', input):
        reply = 'Fine. Be that way!'
    
    # if (no lowercase letters and numbers) or
    # (no lowercase letters and ends in a '!')
    # it's a yell
    elif re.match('^[^a-zäöü0-9]+$', input) or \
    re.match('^[^a-zäöü]+\!$', input):
        reply = 'Whoa, chill out!'
    
    # if ends in a '?', it's a question
    elif re.match('^.+\?$', input):
        reply = 'Sure.'

    # otherwise
    else:
        reply = 'Whatever.'
        
    return reply
