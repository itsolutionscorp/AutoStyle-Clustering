#!/usr/bin/python


def hey(TellBob):
    if TellBob[-1:] == '?' and not TellBob.isupper():
        return 'Sure.'
    elif len(TellBob) == 0 or TellBob.isspace():
        return 'Fine. Be that way!'
    elif TellBob.isupper():
        return 'Whoa, chill out!'
    else:
        return 'Whatever.'
