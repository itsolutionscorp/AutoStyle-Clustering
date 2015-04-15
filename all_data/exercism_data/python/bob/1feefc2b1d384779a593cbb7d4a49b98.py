class Bob(object):

    def is_empty(self, message):
        """Check if message doesn't really say anything."""

        return not message or not message.strip()

    def is_yelling(self, message):
        """Check if message consists of yelling."""

        # Check if there are no lowercase letters.
        return message.isupper()

    def is_question(self, message):
        """Check if message is a question."""

        return message.endswith('?')

    def hey(self, message):
        """Returns Bob's response to message.

        @param message: message to Bob
        @type message: string

        @return: string with Bob's response
        """

        if self.is_empty(message):
            return 'Fine. Be that way!'

        if self.is_yelling(message):
            return 'Woah, chill out!'

        if self.is_question(message):
            return 'Sure.'

        return 'Whatever.'
