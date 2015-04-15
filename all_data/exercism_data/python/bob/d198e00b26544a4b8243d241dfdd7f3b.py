class Bob:
    def hey(self, converse):
        if self._isSilence(converse):
            response = 'Fine. Be that way.'
        elif self._isShouting(converse):
            response = 'Woah, chill out!'
        elif self._isQuestion(converse):
            response = "Sure."
        else:
            response = "Whatever."
        return response

    def _isSilence(self, converse):
        return (converse is None or
            converse.isspace() or
            converse is '')
 
    def _isShouting(self, converse):
        return converse.isupper()

    def _isQuestion(self, converse):
        return converse.endswith("?")
