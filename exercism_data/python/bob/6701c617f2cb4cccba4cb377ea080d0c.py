#!/usr/bin/env python

class Bob:
    def hey(self, message):
        message = message.strip()
        if len(message) == 0:
            return "Fine. Be that way!"
        elif message.isupper():
            return "Woah, chill out!"
        elif message.endswith('?'):
            return "Sure."
        else:
            return "Whatever."
