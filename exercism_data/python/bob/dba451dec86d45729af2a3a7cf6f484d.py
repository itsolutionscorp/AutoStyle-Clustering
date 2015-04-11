#!/usr/bin/python

def getAlphas(string):
    return (c for c in string if c.isalpha())

def hey(string):
    string = string.strip()

    if not string:
        return "Fine. Be that way!"

    if any(getAlphas(string)) and all(c.isupper() for c in getAlphas(string)):
        return "Whoa, chill out!"

    if string.endswith("?"):
        return "Sure."

    return "Whatever."
