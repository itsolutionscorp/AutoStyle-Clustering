# -*- coding: utf-8 -*-

from __future__ import unicode_literals

def hey(inputPhrase):
    if inputPhrase.isupper():
        return 'Whoa, chill out!'
    elif inputPhrase[-1:] == '?':
        return 'Sure.'
    elif inputPhrase.isspace() or inputPhrase == '':
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
