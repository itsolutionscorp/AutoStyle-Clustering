#!/usr/bin/env python

def hey(message):
    if message == None or message.strip() == "":
        return "Fine. Be that way!"
    elif message.isupper():
        return "Whoa, chill out!"
    elif message.endswith('?'):
        return "Sure."
    else:
        return "Whatever."
