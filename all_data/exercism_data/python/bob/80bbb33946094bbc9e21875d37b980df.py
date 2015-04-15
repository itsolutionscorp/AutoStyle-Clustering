class Bob:

    def hey(self, input_string):
        if not input_string.isupper() and input_string.endswith("?"):
            return "Sure."
        if input_string.isupper():
            return "Woah, chill out!"
        if len(input_string.strip()) == 0:
            return "Fine. Be that way!"
        return "Whatever."
