class Bob:
    """ Reflects the repsonse of a teenager """

    def hey(self, text):
        if text.isspace() or not text:
            return "Fine. Be that way!"
        if text.isupper():
            return "Woah, chill out!"
        if text.endswith("?"):
            return "Sure."
        else:
            return "Whatever."
