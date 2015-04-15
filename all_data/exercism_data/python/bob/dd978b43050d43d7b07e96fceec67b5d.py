class Bob:
    def hey(self, x):

        if x.isupper():
            return "Woah, chill out!"

        if x.endswith("?"):
            return "Sure."

        if x.strip() == "":
            return "Fine. Be that way!"

        else:
            return "Whatever."
