class Message(object):
    def __init__(self, message):
        self._message = message

    def is_empty(self):
        return not self._message or not self._message.strip()

    def is_question(self):
        return self._message[-1] == '?'

    def is_yelling(self):
        return self._message.isupper()


class Bob(object):

    def hey(self, message=''):
        msg = Message(message)
        if msg.is_empty():
            return 'Fine. Be that way.'
        elif msg.is_yelling():
            return 'Woah, chill out!'
        elif msg.is_question():
            return 'Sure.'
        else:
            return 'Whatever.'