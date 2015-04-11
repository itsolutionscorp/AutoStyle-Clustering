__RESPONSES__ = {
    'meaningless': 'Fine. Be that way.',
    'angry': 'Woah, chill out!',
    'question': 'Sure.',
    'default': 'Whatever.'
}

class Bob(object):
    """Bob"""

    def hey(self, message):
        if (self._is_meaningless(message)):
            return self._respond('meaningless')
        if (self._is_angry(message)):
            return self._respond('angry')
        if (self._is_question(message)):
            return self._respond('question')

        return self._respond('default')

    def _is_meaningless(self, message):
        return message is None or message.strip() == ''

    def _is_angry(self, message):
        return message.isupper()

    def _is_question(self, message):
        return message.endswith('?')

    def _respond(self, that):
        if (__RESPONSES__.has_key(that)):
            return __RESPONSES__.get(that)
