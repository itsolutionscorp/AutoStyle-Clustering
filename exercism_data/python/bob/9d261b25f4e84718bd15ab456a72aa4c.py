class Bob(object):
    """Bob is a lackadaisical teenager who really doesn't say much."""

    def hey(self, message):
        """Returns an appropriate response to the message"""

        if self.isquiet(message):
            return "Fine. Be that way!"

        # As per tests, yell takes precedence over question.
        if self.isyell(message):
            return "Woah, chill out!"

        if self.isquestion(message):
            return "Sure."

        return "Whatever."

    def isquiet(self, message):
        return not message or not message.strip()

    def isyell(self, message):
        return message.isupper()

    def isquestion(self, message):
        return message.endswith('?')
