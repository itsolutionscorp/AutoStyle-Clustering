import re

def is_empty(what):
    return re.sub(r"\W", "", what) == ""

def is_question(what):
    return what[-1] == "?"

def is_yelling(what):
    return re.search(r"[A-Z]", what) and what.upper() == what

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
