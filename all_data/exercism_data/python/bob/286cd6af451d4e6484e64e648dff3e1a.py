# -*- coding: utf-8 -*-
from __future__ import unicode_literals

#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what.isupper():
        return 'Whoa, chill out!'
    elif what.strip() == '':
        return 'Fine. Be that way!'
    elif what.strip()[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
