class Bob():
    def hey(self, phrase):
        if phrase.replace(" ", "") == "":
            return "Fine. Be that way!"
        elif phrase.replace(", ", "").isdigit() and phrase[-1] != "?":
            return "Whatever."
        elif phrase == phrase.upper() and phrase.replace("?", "").isdigit() == False:
            return "Woah, chill out!"
        elif phrase[-1] == "?":
            return "Sure."
        else:
            return "Whatever."
