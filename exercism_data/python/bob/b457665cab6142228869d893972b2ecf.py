class Bob:
    def hey(self, phrase):
        if phrase.strip('?').isupper():
            return "Woah, chill out!"
        elif phrase.endswith('?'):
            return "Sure."
        elif not len(phrase) or phrase.isspace():
            return "Fine. Be that way!"
        else:
            return "Whatever."
