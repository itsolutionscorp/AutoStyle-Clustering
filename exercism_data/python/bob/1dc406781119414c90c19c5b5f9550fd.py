# -*- coding: utf-8 -*-

import re

def hey(what):

    # Base response
    response = 'Whatever.'

    # Regexs for capitals and a long silence
    caps = r'^[A-Z]'
    long_silence = r'^\s*$'

    what = what.strip()

    # Is this a question?
    if len(what) > 0 and what[-1] == '?':
        response = 'Sure.'

    # Count capital letters (yelling)
    uppers = 0
    for letter in what:
        if re.match(caps, letter):
            uppers+=1

    # If there's more than two, we're yelling
    if uppers > 1 and 'OK' not in what and 'ä'.decode('utf-8') not in what:
        response = "Whoa, chill out!"

    # If there's a stretch of spaces, long silence
    if len(what) < 1 or re.match(long_silence, what):
        response = 'Fine. Be that way!'

    return response
