class Bob:
    def hey(self, input):
        input = input.strip()
        output = "Whatever."
        if not input:
            output = "Fine. Be that way!"
        elif input.isupper():
            output = "Woah, chill out!"
        elif input.endswith("?"):
            output = "Sure."
        return output
