# -*- coding: utf-8 -*-

def hey(str):
    if str == '' or str.isspace():
        return 'Fine. Be that way!'
    elif str.isupper():
        return 'Woah, chill out!'
    elif str[-1] == '?':
        return 'Sure.'

    return 'Whatever.'
