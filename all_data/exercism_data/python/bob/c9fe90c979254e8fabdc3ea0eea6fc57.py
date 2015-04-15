class Bob(object):
    def hey(self, statement):
        if not statement.strip():
            return "Fine. Be that way!"
        elif statement.isupper():
            return "Woah, chill out!"
        elif statement.endswith("?"):
            return "Sure."
        else:
            return "Whatever."
