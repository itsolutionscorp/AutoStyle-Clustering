import re

def is_empty(what):
    return not what or what.isspace()

def is_question(what):
    return what and what[-1] == "?"

def is_yelling(what):
    return what and what.isupper()

class Bob:
    def hey(self, what):
        if is_empty(what):
            return "Fine. Be that way!"
        elif is_yelling(what):
            return "Woah, chill out!"
        elif is_question(what):
            return "Sure."
        else:
            return "Whatever."
