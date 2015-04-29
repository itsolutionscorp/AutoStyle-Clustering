class Bob(object):

    @staticmethod
    def hey(message):
        message = Message(message)
        if message.shouting():
            response = 'Woah, chill out!'
        elif message.question():
            response = 'Sure.'
        elif message.silence():
            response = 'Fine. Be that way!'
        else:
            response = 'Whatever.'
        return response


class Message(object):

    def __init__(self, message):
        self.message = message

    def shouting(self):
        return self.message.isupper()

    def question(self):
        return self.message.endswith('?')

    def silence(self):
        return self.message.strip() == ''
