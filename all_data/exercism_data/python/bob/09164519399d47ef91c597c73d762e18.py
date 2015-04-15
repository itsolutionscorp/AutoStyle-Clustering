# -*- coding: utf-8 -*-

def hey(message):
    if message.isupper():
        return "Whoa, chill out!"
    elif message.endswith("?"):
        return "Sure."
    elif message.isspace():
        return "Fine. Be that way!"
    elif message == "":
        return "Fine. Be that way!"
    else:
        return "Whatever."
