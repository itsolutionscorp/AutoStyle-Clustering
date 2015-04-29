import re

class Bob:
    "A simple implementation of the Bob Teenager Class"

    def hey(self, message):
        if re.match(r'^\s*$', message):
            return 'Fine. Be that way!'
        if message.upper() == message and re.search(r'[A-Z]', message):
            return 'Woah, chill out!'
        if message[-1] == '?':
            return "Sure."

        return "Whatever."
