# -*- coding: utf-8 -*-

def hey(message):
    if message.isupper():                  # shouting
        return "Whoa, chill out!"
    elif message.endswith("?"):            # question
        return "Sure."
    elif not message or message.isspace(): # silent
        return "Fine. Be that way!"
    else:                                  # all else
        return "Whatever."
