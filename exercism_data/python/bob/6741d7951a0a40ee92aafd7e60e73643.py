class Bob(object):
    def hey(self, phrase):
        if not phrase or phrase.isspace():
            return "Fine. Be that way!"
        elif phrase.isupper():
            return "Woah, chill out!"
        elif phrase.endswith("?"):
            return "Sure."
        else:
            return "Whatever."
