#!/usr/bin/env python


def hey(what):
    statement = what.strip()  # ' '.join(what.split())  # strips any excess whitespace or tabs
    if statement == '':
        return 'Fine. Be that way!'
    elif statement.isupper():  # Is much clearer than 'what == what.upper() and what != what.lower()'
        return 'Whoa, chill out!'
    elif statement.endswith('?'):  # not 'elif '?' in what:', also, 'what.endswith('?')' is more clear than 'what[-1] == '?''
        return 'Sure.'
    else:
        return 'Whatever.'
