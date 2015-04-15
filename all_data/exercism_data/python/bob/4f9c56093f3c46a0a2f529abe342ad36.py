#!/usr/bin/env python

# Lackadasical teenager response simulator.
def hey(message):
    message = message.strip()

    if not message:
        return "Fine. Be that way!"

    if is_shouting(message):
        return "Whoa, chill out!"

    if is_question(message):
        return "Sure."

    return "Whatever."

def is_question(message):
    return message.endswith("?")

def is_shouting(message):
    return message.isupper()
