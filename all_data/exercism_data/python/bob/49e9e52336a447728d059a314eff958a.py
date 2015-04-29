# -*- coding: utf-8 -*-

def hey(message):
    if isShouting(message):
        return "Whoa, chill out!"
    elif isQuestion(message):
        return "Sure."
    elif isSilent(message):
        return "Fine. Be that way!"
    else:
        return "Whatever."

def isShouting(message):
    capcount = 0
    lowcount = 0
    for c in message:
        if c.isupper():
            capcount += 1
        elif c.islower():
            lowcount += 1
    return capcount > lowcount

def isQuestion(message):
    return message.endswith("?")

def isSilent(message):
    return (not message or message.isspace())
