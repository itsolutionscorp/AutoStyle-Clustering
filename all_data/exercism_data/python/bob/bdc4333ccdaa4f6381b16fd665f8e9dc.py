# -*- coding: utf-8 -*-
#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    what = what.strip()
    if not what:
        return 'Fine. Be that way!'
    if what.endswith('?') and not what[0:-1].isupper():
        return 'Sure.'
    if what[0:-1].isupper() or what[0:-1].isupper() and what.endswith('!'):
        return 'Whoa, chill out!'
    return 'Whatever.'
