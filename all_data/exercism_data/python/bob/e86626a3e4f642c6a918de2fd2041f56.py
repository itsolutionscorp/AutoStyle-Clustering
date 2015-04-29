class Bob:
    def hey(self, phrase):
        if not phrase:
            return "Fine, be that way."
        elif phrase == phrase.upper():
            return "Woah, chill out!"
        elif phrase.endswith("?"):
            return "Sure."
        else:
            return "Whatever."
