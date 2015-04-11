class Bob(object):
    """Bob implements a lackadaisical teenager."""

    @staticmethod
    def hey(what):
        """Address Bob by saying `what`."""
        if not what.strip():
            return 'Fine. Be that way!'
        if what == what.upper() and what != what.lower():
            return 'Woah, chill out!'
        if what.endswith('?'):
            return 'Sure.'
        return 'Whatever.'
