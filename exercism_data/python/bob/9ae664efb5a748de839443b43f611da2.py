# -*- coding: utf-8 -*-

def hey(saying):
    if saying.isupper():
        return "Woah, chill out!"
    elif saying.isspace() or saying == '':
        return "Fine. Be that way!"
    elif saying.endswith('?'):
        return "Sure."
    else:
        return "Whatever."
