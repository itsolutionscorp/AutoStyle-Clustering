#!/usr/bin/env python

def is_yelling(msg):
    return msg.isupper()

def is_question(msg):
    return msg[-1] == '?'

def hey(what):
    what = what.strip()
    if what == "":
        return "Fine. Be that way!"
    elif is_yelling(what):
        return "Whoa, chill out!"
    elif is_question(what):
        return "Sure."
    else:
        return "Whatever."
