class Bob(object):
    def hey(self, message):
        if self._is_silent(message):
            return 'Fine. Be that way!'
        if self._is_angry(message):
            return 'Woah, chill out!'
        if self._is_question(message):
            return 'Sure.'
        return 'Whatever.'

    def _is_silent(self, message):
        return message is None or message.strip() == ''

    def _is_angry(self, message):
        return message.isupper()

    def _is_question(self, message):
        return message.endswith('?')
