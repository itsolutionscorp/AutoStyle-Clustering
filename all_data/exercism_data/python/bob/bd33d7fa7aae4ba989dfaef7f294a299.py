from __future__ import unicode_literals
import sys

def is_all_caps(message):
    has_letters = False
    for char in message:
        if not char.isalpha():
            continue
        elif char.islower():
            return False
        has_letters = True
    return has_letters

class Bob:
    def hey(self, message):
        if message is None or message.strip() == '':
            return "Fine. Be that way!"
        elif is_all_caps(message):
            return 'Woah, chill out!'
        elif message[-1] == '?':
            return "Sure."
        else:
            return "Whatever."
