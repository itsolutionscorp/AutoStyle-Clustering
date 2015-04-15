class Bob:
    def hey(self, converse):
        if self.issilence(converse):
            return 'Fine. Be that way.'
        elif converse.isupper():
            return 'Woah, chill out!'
        elif converse.endswith("?"):
            return "Sure."
        return "Whatever."

    def issilence(self, converse):
        return (converse is None or
            converse is '' or
            converse.isspace())
