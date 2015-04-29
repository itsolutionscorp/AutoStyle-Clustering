import re

class Bob:
    def hey(self, s):
        if s==s.upper() and re.search(r"[A-Z]", s): return "Woah, chill out!"
        if len(s) and s[-1]=="?": return "Sure."
        if s.strip()=="": return "Fine. Be that way!"
        return "Whatever."
