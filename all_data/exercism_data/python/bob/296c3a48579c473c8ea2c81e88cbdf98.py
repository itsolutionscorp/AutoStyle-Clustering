class Bob(object):
    """Bob imitates the response of a lackadaisical teenager."""
    def hey(self, speech):
        if not speech.strip():
            # If nothing was actually said
            return "Fine. Be that way!"
        elif speech.isupper():
            # Bob responds strongly to yelling
            return "Woah, chill out!"
        elif speech.endswith('?'):
            # A proper question is likely to end up here
            return "Sure."
        # Anything else elicits a tepid response
        return "Whatever."
