# -*- coding: utf-8 -*-
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    stripped_what = what.strip()
    if stripped_what == '':
        return 'Fine. Be that way!'
    elif stripped_what.isupper():
        return 'Whoa, chill out!'
    elif stripped_what.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
