# -*- coding: utf-8 -*-

def hey(text):
    text = text.strip()
    if not text:
        return "Fine. Be that way!"
    elif text.isupper():
        return "Whoa, chill out!"
    elif text.endswith("?"):
        return "Sure."
    else:
        return "Whatever."
