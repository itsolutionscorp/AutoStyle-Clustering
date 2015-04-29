# -*- coding: utf-8 -*-

def hey(what):
    what = what.strip()
    if what == '':
        return 'Fine. Be that way!'
    elif what.upper() == what and any(char.isalpha() for char in what):
        return 'Whoa, chill out!'
    elif what.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
    return
