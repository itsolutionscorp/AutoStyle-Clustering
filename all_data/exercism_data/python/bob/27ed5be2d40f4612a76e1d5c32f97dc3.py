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
    # If the message is in all uppercase characters, it's considered
    # shouting. However, we need to make sure the message isn't just
    # something that consists of characters with no uppercase or
    # lowercase.
    return message.upper() == message and message.lower() != message
