class Bob:
    def hey(self, stmt):
        if stmt is None or stmt.strip() == "":
            return "Fine. Be that way!"

        if stmt.upper() == stmt:
            return "Woah, chill out!"

        if stmt[-1] == '?':
            return "Sure."

        return "Whatever."
