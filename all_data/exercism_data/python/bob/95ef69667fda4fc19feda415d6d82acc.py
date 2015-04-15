class Bob:

    def hey(self, input_string):
        if input_string.isupper():
            return "Woah, chill out!"
        if input_string.endswith("?"):
            return "Sure."
        if len(input_string.strip()) == 0:
            return "Fine. Be that way!"
        return "Whatever."
