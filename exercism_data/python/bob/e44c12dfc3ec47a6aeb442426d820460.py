#!/usr/bin/env python3

def hey(what:str) -> bool:
    what = what.strip()
    if is_yelling(what):
        return "Whoa, chill out!"
    if is_question(what):
        return "Sure."
    if not what:
        return "Fine. Be that way!"
    
    return "Whatever."

def is_question(text):
    return text.endswith("?")

def is_yelling(text):
    return text.isupper()
