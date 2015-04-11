class Message(object):
    def __init__(self, message):
        self._message = message

    def is_empty(self):
        return not self._message or not self._message.strip()

    def is_question(self):
        return self._message.endswith('?')

    def is_yelling(self):
        return self._message.isupper()


class Response(object):
    fine = 'Fine. Be that way.'
    chill = 'Woah, chill out!'
    sure = 'Sure.'
    whatever = 'Whatever.'


class Bob(object):

    def hey(self, message=''):
        msg = Message(message)
        if msg.is_empty():
            response = Response.fine
        elif msg.is_yelling():
            response = Response.chill
        elif msg.is_question():
            response = Response.sure
        else:
            response = Response.whatever

        return response
