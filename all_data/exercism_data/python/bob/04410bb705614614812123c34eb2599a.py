class Bob:
    """Bob's responses"""

    def hey(self, string):
        # If nothing was actually said
        if not string.strip():
            return "Fine. Be that way!"
        # Bob responds strongly to yelling
        elif string.isupper():
            return "Woah, chill out!"
        # A proper question is likely to end up here
        elif string.endswith('?'):
            return "Sure."
        # Anything else elicits a tepid response
        return "Whatever."
