#!/usr/bin/python
# -*- coding: utf8 -*-
#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):

    # empty or non printable caracters sentence -> 'Fine. Be that way!',
    if what.isspace() or len(what) == 0:
        return 'Fine. Be that way!'
    # sentence without lower letters -> 'Whoa, chill out!'
    elif what.isupper():
        return 'Whoa, chill out!'
    # sentence with question ending -> 'Sure.'
    elif what.endswith('?'):
        return 'Sure.'
    # rest -> 'Whatever.'
    return 'Whatever.'
