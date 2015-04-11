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
        return re.match(".*\?$", string)

    def is_exclamation(self, string):
        return (string.upper() == string) and re.match(".*[a-zA-Z].*", string)

    def is_empty(self, string):
        a = re.compile("^\s*$")
        return a.match(string)
