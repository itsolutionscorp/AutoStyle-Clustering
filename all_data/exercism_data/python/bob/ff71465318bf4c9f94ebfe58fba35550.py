#!/usr/bin/env python
# encoding: utf-8


def hey(string):
    if len(string.strip()) == 0:
        return 'Fine. Be that way!'
    elif string.isupper():
        return 'Whoa, chill out!'
    elif string.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
