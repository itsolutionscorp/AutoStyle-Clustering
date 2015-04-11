class Bob(object):

    def hey(self, message):
        if self._is_silence(message):
            return 'Fine. Be that way!'
        elif self._is_shouting(message):
            return 'Woah, chill out!'
        elif self._is_question(message):
            return 'Sure.'
        else:
            return 'Whatever.'

    def _is_silence(self, message):
        return message is None or not message.strip()

    def _is_question(self, message):
        return message.endswith('?')

    def _is_shouting(self, message):
        return message.isupper()
