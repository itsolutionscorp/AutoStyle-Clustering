import re

class Bob:
    def hey(self, string):
        last_char = string[-1::]

        if not len(string) or not len(string.replace(' ','')):
            return "Fine. Be that way!"
        elif string==string.upper() and re.match("[^a-zA-Z]*[a-zA-Z]+[^a-zA-Z]*",string) is not None:
            return "Woah, chill out!"
        elif last_char == "?":
            return "Sure."
        else:
            return "Whatever."
