class Bob:
    def hey(self, input):
        input_string = input.strip()
        if input_string.isupper():
            return "Woah, chill out!"
        elif input_string.endswith("?"):
            return "Sure."
        elif not input_string:
            return "Fine. Be that way!"
        else:
            return "Whatever."
