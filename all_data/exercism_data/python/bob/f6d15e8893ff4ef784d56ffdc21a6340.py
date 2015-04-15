class Bob:
    def hey(self, statement):
        if not statement.strip():
            return "Fine. Be that way."
        elif statement == statement.upper():
            return "Woah, chill out!"
        elif statement[-1] == '?':
            return "Sure."
        else:
            return "Whatever."
