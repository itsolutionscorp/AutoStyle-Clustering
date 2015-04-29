class Interpretation(object):
    """Interprets a message."""

    def __init__(self, message):
        self.message = message

    def is_empty(self):
        """Check if message doesn't really say anything."""

        return not self.message or not self.message.strip()

    def is_yelling(self):
        """Check if message consists of yelling."""        

        return self.message.isupper()

    def is_question(self):
        """Check if message is a question."""

        return self.message.endswith('?')
    

class Bob(object):

    def hey(self, message):
        """Returns Bob's response to message.

        @param message: message to Bob
        @type message: string

        @return: string with Bob's response
        """

        interpretation = Interpretation(message)

        if interpretation.is_empty():
            return 'Fine. Be that way!'
        elif interpretation.is_yelling():
            return 'Woah, chill out!'
        elif interpretation.is_question():
            return 'Sure.'
        else:
            return 'Whatever.'
