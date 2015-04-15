#!/usr/bin/env python


def hey(what):
    what = what.strip()  # ' '.join(what.split())  # strips any excess whitespace or tabs
    if what == '':
        return 'Fine. Be that way!'
    elif what.isupper():  # Is much clearer than 'what == what.upper() and what != what.lower()'
        return 'Whoa, chill out!'
    elif what.endswith('?'):  # not 'elif '?' in what:', also, 'what.endswith('?')' is more clear than 'what[-1] == '?''
        return 'Sure.'
    else:
        return 'Whatever.'
