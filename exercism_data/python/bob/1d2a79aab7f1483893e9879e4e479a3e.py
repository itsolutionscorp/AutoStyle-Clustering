class Bob:
    def hey(self, inputString):
        if self._isSilence(inputString):
            return 'Fine. Be that way.'
        if self._isShouting(inputString):
            return 'Woah, chill out!'
        if self._isQuestion(inputString):
            return 'Sure.'
        return 'Whatever.'

    def _isShouting(self, inputString):
        return inputString.upper() == inputString

    def _isQuestion(self, inputString):
        return '?' == inputString[-1]

    def _isSilence(self, inputString):
        return None == inputString or '' == inputString.strip()
