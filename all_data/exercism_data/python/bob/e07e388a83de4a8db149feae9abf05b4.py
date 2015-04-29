class Bob(object):
    """Bob: a lackadaisical teenager."""

    @staticmethod
    def hey(message):
        """Evaluates an input message and answers accordingly."""
        if message.isupper():
            return 'Woah, chill out!'
        elif message.endswith('?'):
            return 'Sure.'
        elif not message or message.isspace():
            return 'Fine. Be that way!'
        return 'Whatever.'
