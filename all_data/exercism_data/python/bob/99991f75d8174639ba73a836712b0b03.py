#!/usr/bin/env python


def hey(what):
    what = ' '.join(what.split())  # strips any excess whitespace or tabs
    if len(what) < 1:
        return 'Fine. Be that way!'
    elif what == what.upper() and what != what.lower():
        return 'Whoa, chill out!'
    elif what.endswith('?'):  # not 'elif '?' in what:' 'what.endswith('?')' is more clear than 'what[-1] == '?''
        return 'Sure.'
    else:
        return 'Whatever.'
