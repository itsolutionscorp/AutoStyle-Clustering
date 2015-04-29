class Bob():
    def hey(self, t):
        if t.isupper():
            return "Woah, chill out!"
        elif len(t) > 0 and t[-1] == '?':
            return "Sure."
        elif len(t) == 0 or t.isspace():
            return "Fine. Be that way!"
        else:
            return "Whatever."
