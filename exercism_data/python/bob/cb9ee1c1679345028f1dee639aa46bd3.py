class Bob:
    def hey(self, input):
        input_string = input.strip()
        if input_string.endswith("?") and not input_string.isupper():
            return "Sure."
        elif not input_string:
            return "Fine. Be that way!"
        elif input_string.isupper():
            if input_string.endswith("!") or input_string.endswith("?"):
                return "Woah, chill out!"
            else:
                return "Woah, chill out!"
        else:
            return "Whatever."
