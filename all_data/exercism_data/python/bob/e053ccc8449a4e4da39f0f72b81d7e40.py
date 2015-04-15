class Bob(object):

    def __init__(self):
        self._message = None

    def hey(self, message=''):
        self._message = message
        if self.is_empty():
            return 'Fine. Be that way.'
        elif self.is_yelling():
            return 'Woah, chill out!'
        elif self.is_question():
            return 'Sure.'
        else:
            return 'Whatever.'

    def is_empty(self):
        return not self._message or not self._message.strip()

    def is_question(self):
        return self._message[-1] == '?'

    def is_yelling(self):
        return self._message.isupper()
