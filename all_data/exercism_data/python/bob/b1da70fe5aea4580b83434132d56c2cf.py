# -*- coding: utf-8 -*-


def hey(string):
    if not string or string.isspace():
        return 'Fine. Be that way!'
    elif string.isupper():
        return 'Whoa, chill out!'
    elif string[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
