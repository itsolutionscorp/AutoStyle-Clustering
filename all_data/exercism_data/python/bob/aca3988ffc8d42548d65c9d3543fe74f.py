class Bob(object):
    """Bob implements a lackadaisical teenager."""

    @staticmethod
    def hey(what):
        """Address Bob by saying `what`."""
        if what == what.upper() and what != what.lower():
            return 'Woah, chill out!'
        if what.endswith('?'):
            return 'Sure.'
        if not what.strip():
            return 'Fine. Be that way!'
        return 'Whatever.'
