import re

class Bob(object):
    def hey(self, message):
        if re.match(r"^\s*$", message):
            return "Fine. Be that way!"
        if message.upper() == message and re.match(r".*[a-zA-Z].*", message) :
            return "Woah, chill out!"
        if re.match(r".+\?$", message):
            return "Sure."
        return "Whatever."
