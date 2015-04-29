#!/usr/bin/env python
# -*- coding: utf-8 -*-
# Skeleton file for the Python "Bob" exercise.

def hey(what):
    if what.isupper():
        answer = 'Whoa, chill out!'
    elif what.endswith('?'):
        answer = 'Sure.'
    elif not what or what.isspace():
        answer = 'Fine. Be that way!'
    else:
        answer = 'Whatever.'

    return answer
