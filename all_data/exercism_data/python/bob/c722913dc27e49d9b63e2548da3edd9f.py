class Bob(object):
    """ Lackadaisical teenager that responds in some special way to your messages """

    def hey(self, message):
        """ Send Bob a message and get a response

        @param message: string that represents an inbound message to Bob
        @return: response string from Bob
        """
        msg = Message(message)
        if msg.is_silent():
            response = 'Fine. Be that way!'
        elif msg.is_shout():
            response = 'Woah, chill out!'
        elif msg.is_question():
            response = 'Sure.'
        else:
            response = 'Whatever.'
        return response


class Message(object):
    """ Represents a string message """

    def __init__(self, message):
        self.message = message.strip() if message else message

    def is_silent(self):
        return not self.message

    def is_shout(self):
        return self.message.isupper()

    def is_question(self):
        return self.message.endswith('?')
