# -*- coding: utf-8 -*-

def hey(text):
    text = text.strip()
    if not text:
        return "Fine. Be that way!"
    if text.isupper():
        return "Whoa, chill out!"
    if text[-1] == "?":
        return "Sure."
    return "Whatever."
