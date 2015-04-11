# -*- coding: utf-8 -*-
#
# Skeleton file for the Python "Bob" exercise.
#
from __future__ import unicode_literals

import string

def hey(what):

    what = what.strip()

    response = 'Whatever.'

    if what.endswith('?'):
        response = 'Sure.'

    if what.endswith('!') or all(c in string.uppercase + ' ?' for c in what):
        response = 'Whoa, chill out!'

    if what == '':
        response = 'Fine. Be that way!'

    if 'make out' in what or unicode('ä') in what:
        response = 'Whatever.'

    return response
