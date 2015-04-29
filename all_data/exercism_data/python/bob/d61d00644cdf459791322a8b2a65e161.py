class Bob:
    def hey(self, phrase):
        if not phrase or not phrase.strip():
            return "Fine. Be that way."
        elif phrase.isupper():
            return "Woah, chill out!"
        elif phrase.endswith("?"):
            return "Sure."
        return "Whatever."
