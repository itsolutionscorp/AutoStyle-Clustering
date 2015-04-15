import re

class Bob:
    def hey(self, statement):
        if self.is_exclamation(statement):
            return "Woah, chill out!"
        elif self.is_empty(statement):
            return "Fine. Be that way!"
        elif self.is_question(statement):
            return "Sure."
        else:
            return "Whatever."

    def is_question(self, string):
        return string.endswith("?")

    def is_exclamation(self, string):
        return string.isupper()

    def is_empty(self, string):
        return re.match("^\s*$", string)
