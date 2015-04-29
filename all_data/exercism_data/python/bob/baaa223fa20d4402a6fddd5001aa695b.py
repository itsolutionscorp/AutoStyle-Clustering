import string

class Bob():
    def hey(self, what):
        if string.strip(what) == "":
            return "Fine. Be that way!"
        if string.upper(what) == what and not string.lower(what) == what:
            return "Woah, chill out!"
        if what[-1] is "?":
            return "Sure."
        if string.lower(what) == what:
            return "Whatever."
        else:
            return "Whatever."
