class Bob():
    def hey(self, phrase):
        if phrase == phrase.upper() and any(x.isalpha() for x in phrase):
            return "Woah, chill out!"
        elif phrase.isspace() or not phrase:
            return "Fine. Be that way!"
        elif phrase[-1] == "?":
            return "Sure."
        else:
            return "Whatever."
