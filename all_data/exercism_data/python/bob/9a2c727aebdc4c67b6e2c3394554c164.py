class Bob:
    def hey(self, text):
        if text.isspace() or len(text) == 0:
            return "Fine. Be that way!"
        if text.isupper():
            return "Woah, chill out!"
        if text.endswith("?"):
            return "Sure."
        else:
            return "Whatever."
