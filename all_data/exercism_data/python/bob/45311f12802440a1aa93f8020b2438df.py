class Bob:
    def hey(self, phrase):
        if not phrase or not phrase.strip():
            return "Fine. Be that way."
        elif phrase.upper() == phrase:
            return "Woah, chill out!"
        elif phrase[-1] == "?":
            return "Sure."
        return "Whatever."
