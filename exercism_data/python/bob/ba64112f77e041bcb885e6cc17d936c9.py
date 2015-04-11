class Bob:

    """A lackadaisical teenager"""

    def hey(self, message):
        """Deliver message to Bob and return his reply"""
        message = message.strip()
        if not message:
            return "Fine. Be that way!"
        elif message.isupper():
            return "Woah, chill out!"
        elif message.endswith('?'):
            return "Sure."
        else:
            return "Whatever."
