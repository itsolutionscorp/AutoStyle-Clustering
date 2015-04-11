import re

class Bob(object):
    def hey(self, message):
        if not message.strip():
            return "Fine. Be that way!"
        if message.isupper():
            return "Woah, chill out!"
        if message.endswith('?'):
            return "Sure."
        return "Whatever."
