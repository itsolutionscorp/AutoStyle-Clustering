class Bob:
    """Bob imitates the response of a lackadaisical teenager."""
    def hey(self, speech):
        # If nothing was actually said
        if not speech.strip():
            return "Fine. Be that way!"
        # Bob responds strongly to yelling
        elif speech.isupper():
            return "Woah, chill out!"
        # A proper question is likely to end up here
        elif speech.endswith('?'):
            return "Sure."
        # Anything else elicits a tepid response
        return "Whatever."
