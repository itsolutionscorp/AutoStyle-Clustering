class Bob(object):
    def hey(self, s):
        if s.isupper():
            return "Woah, chill out!"
        elif s.endswith("?"):
            return "Sure."
        elif s.strip() == '':
            return "Fine. Be that way!"

        return "Whatever."
