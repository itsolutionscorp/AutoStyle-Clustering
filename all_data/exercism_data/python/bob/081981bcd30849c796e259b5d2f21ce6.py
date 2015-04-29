# -*- coding: utf-8 -*-

def hey(what):
    if what.isupper():
        return "Whoa, chill out!"
    elif not what.strip():
        return "Fine. Be that way!"
    elif "?" in what[-1:]:
        return "Sure."
    else:
        return "Whatever."
