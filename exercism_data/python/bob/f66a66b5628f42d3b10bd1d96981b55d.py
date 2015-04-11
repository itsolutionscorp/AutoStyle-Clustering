import re

class Bob:
    def hey(self, str):
        if str.isspace() or not str.strip(): return "Fine. Be that way!"
        if str.isupper(): return "Woah, chill out!"
        if str.endswith("?"): return "Sure."
        return "Whatever."
