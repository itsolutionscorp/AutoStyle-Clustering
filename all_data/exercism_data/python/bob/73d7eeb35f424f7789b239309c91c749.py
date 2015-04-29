class Bob(object):
    """A lackadaisical teenager"""

    @staticmethod
    def hey(message):
        """Return Bob's response to anything."""
        if not message.strip():
            return 'Fine. Be that way!'
        elif message.isupper():
            return 'Woah, chill out!'
        elif message.endswith('?'):
            return 'Sure.'
        return 'Whatever.'
