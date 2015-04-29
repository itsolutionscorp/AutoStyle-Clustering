# -*- coding: utf-8 -*-

from __future__ import unicode_literals

def hey(what):

    if not what.strip():
        return 'Fine. Be that way!'
    elif what.isupper():
        return 'Whoa, chill out!'
    elif what.strip().endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
