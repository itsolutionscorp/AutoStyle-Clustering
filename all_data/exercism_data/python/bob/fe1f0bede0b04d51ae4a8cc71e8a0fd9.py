class Bob:
    def hey(self, message):
        if not message:
            message = ' '
        self.message = message
        if self._is_just_addresed():
            return 'Fine. Be that way.'
        if self._is_yelled_at():
            return 'Woah, chill out!'
        if self._is_asked():
            return 'Sure.'
        return 'Whatever.'

    def _is_asked(self):
        return self.message.endswith('?')

    def _is_yelled_at(self):
        return self.message.isupper()

    def _is_just_addresed(self):
        return self.message.isspace()
