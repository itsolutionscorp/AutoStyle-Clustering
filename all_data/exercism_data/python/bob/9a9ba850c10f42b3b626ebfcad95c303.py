# -*- coding: utf-8 -*-

def hey(string):
    if not string or string.isspace():
        return "Fine. Be that way!"
    elif string == string.upper() and any(letter.isalpha() for letter in string):
        return "Whoa, chill out!"
    elif string.endswith("?"):
        return "Sure."
    else:
        return "Whatever."
