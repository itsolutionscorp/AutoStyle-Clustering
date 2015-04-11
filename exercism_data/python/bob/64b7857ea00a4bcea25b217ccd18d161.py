class Bob:
    def hey(self, message):
        if self._is_silent(message):
            return 'Fine, be that way.'
        if self._is_loud(message):
            return 'Woah, chill out!'
        if self._is_asking(message):
            return 'Sure.'
        return 'Whatever.'

    def _is_loud(self, message):
        return message.upper() == message

    def _is_asking(self, message):
        return message[-1] == '?'

    def _is_silent(self, message):
        return message == ''
