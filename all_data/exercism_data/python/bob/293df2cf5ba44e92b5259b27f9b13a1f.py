class Bob:
    def hey(self, input):
        input_string = input.strip()
        if input_string.endswith("?") and not input_string.isupper():
            return "Sure."
        elif not input_string:
            return "Fine. Be that way!"
        elif input_string.isupper():
                return "Woah, chill out!"
        else:
            return "Whatever."
