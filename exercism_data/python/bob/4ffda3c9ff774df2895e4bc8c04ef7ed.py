class Bob:

    def __init__(self):
        pass

    def hey(self, converse):
        if converse is None:
            return 'Fine. Be that way.'
        elif converse is '' or converse.isspace():
            return 'Fine. Be that way.'
        elif converse.isupper():
            return 'Woah, chill out!'
        elif converse[-1] == "?":
            return "Sure."
        return "Whatever."
