class Bob(object):

    def hey(self, text):
        if text.isupper():
            return "Woah, chill out!"
        if text.endswith("?"):
            return "Sure."
        if text.strip() == "":
            return "Fine. Be that way!"
        return "Whatever."
