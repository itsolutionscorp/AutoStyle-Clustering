class Bob(object):
    def hey(self, s):
        s = s.strip()
        if not s:
            return "Fine. Be that way!"
        if s.isupper():
            return "Woah, chill out!"
        if s.endswith("?"):
            return "Sure."
        return "Whatever."
