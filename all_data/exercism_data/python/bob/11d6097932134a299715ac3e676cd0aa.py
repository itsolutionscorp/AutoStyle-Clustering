import re

class Bob:
    def hey(self, message):
        if message.isupper():
            return "Woah, chill out!"
        elif re.compile('.*\?$').match(message):
            return "Sure."
        elif not message.strip():
            return "Fine. Be that way!"
        else:
            return "Whatever."
