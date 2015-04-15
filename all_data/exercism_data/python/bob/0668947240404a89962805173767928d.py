class Bob(object):
    """Bob is a lackadaisical teenager who really doesn't say much."""

    def hey(self, message):
        """Returns an appropriate response to the message"""
        # Test for quiet first as it catches None.
        if self.isquiet(message):
            return "Fine. Be that way!"
        # As per tests, yell takes precedence over question.
        elif self.isyell(message):
            return "Woah, chill out!"
        elif self.isquestion(message):
            return "Sure."
        else:
            return "Whatever."

    def isquiet(self, message):
        # Check if nothing was said at all.
        return not message or message.strip() == ""

    def isyell(self, message):
        # Check if the message is being yelled.
        return message.upper() == message

    def isquestion(self, message):
        # Check if the message is a question.
        return message.endswith('?')
