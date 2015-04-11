import re

class Bob():
    def hey(self, phrase):
        if len(phrase.strip()) == 0:
            return "Fine. Be that way!"
        elif phrase.isupper():
            return "Woah, chill out!"
        elif re.search("[?]$", phrase):
            return "Sure."
        else:
            return "Whatever."
