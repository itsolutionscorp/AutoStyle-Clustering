# -*- coding: utf-8 -*-

from __future__ import unicode_literals

def hey(input):

    input = input.strip()
    print(input)

    if len(input) == 0:
        return 'Fine. Be that way!'
    elif input.isupper():
        return 'Whoa, chill out!'
    elif input[-1:] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
