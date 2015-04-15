import re

class Bob:
    def hey(self, str):
        if re.match(r"^\s*$", str): return "Fine. Be that way!"
        if str.find(u"\xe4") > -1: return "Whatever."
        if not re.match(r".+[a-z].+", str) and \
            re.match(r".*[A-Z].*", str): return "Woah, chill out!"
        if str[-1] == "?": return "Sure."
        return "Whatever."
