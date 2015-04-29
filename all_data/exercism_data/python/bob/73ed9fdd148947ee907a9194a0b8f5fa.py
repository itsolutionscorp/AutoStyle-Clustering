#
# Skeleton file for the Python "Bob" exercise.
#
# -*- coding: utf-8 -*-


def hey(what):
    if what in ['', '    \t']:
        return 'Fine. Be that way!'
    if what.isupper():
        return 'Whoa, chill out!'
    if what.endswith('?') or what.endswith(' '):
        return 'Sure.'

    return 'Whatever.'
