# -*- coding: utf-8 -*-

from __future__ import unicode_literals


#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    response = 'Whatever.'
    if len(what) == 0:
        response = 'Fine. Be that way!'
    elif what.upper() == what and any(c.isalpha() for c in what):
        response = 'Whoa, chill out!'
    elif what[-1] == '?':
        response = 'Sure.'

    return response
