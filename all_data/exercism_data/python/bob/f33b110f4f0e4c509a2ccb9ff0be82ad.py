class Bob(object):
    def hey(self, s):
        s = s.rstrip()
        if not s:
            return "Fine. Be that way!"
        if s.isupper():
            return "Woah, chill out!"
        if s[-1] == "?":
            return "Sure."
        return "Whatever."
