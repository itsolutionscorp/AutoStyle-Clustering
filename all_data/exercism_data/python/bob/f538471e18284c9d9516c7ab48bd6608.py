#!/usr/bin/python

def hey(what):
    if any(w.isalnum() for w in what):
        if what.isupper():
            return 'Whoa, chill out!'
        elif what.endswith('?'):
            return 'Sure.'
        else:
            return 'Whatever.'
    else:
        return 'Fine. Be that way!'
