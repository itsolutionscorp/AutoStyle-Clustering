class Bob(object):
    def hey(self, s):
        if s.isupper():
            return "Woah, chill out!"
        elif s and s[-1] == "?":
            return "Sure."
        elif not s or not s.strip():
            return "Fine. Be that way!"
        else:
            return "Whatever."
