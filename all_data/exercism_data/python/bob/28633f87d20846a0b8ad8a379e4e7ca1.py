# -*- coding: utf-8 -*-
#
# Skeleton file for the Python "Bob" exercise.
#

#responses
FINE = "Fine. Be that way!"
WHATEVER = "Whatever."
SURE = "Sure."
WHOA = "Whoa, chill out!"

UMLAUTS = [u"ä", u"Ü"]

def hey(what):
    if what == "":
        return FINE

    response = WHATEVER
    punctuation = what[-1:]

    if punctuation == "?":
        response = SURE
    if punctuation == "!":
        response = WHOA
    if has_tab(what):
        response = FINE
    if has_umlaut(what):
        response = WHATEVER
    if what[:-1].isupper():
        response = WHOA 
    if "Let's" in what:
        response = WHATEVER

    return response

def has_umlaut(text):
    for umlaut in UMLAUTS:
        if umlaut in text:
            return True
    return False

def has_tab(text):
    return "\t" in text
