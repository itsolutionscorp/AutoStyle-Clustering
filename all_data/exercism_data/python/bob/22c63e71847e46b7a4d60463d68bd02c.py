class Bob:
    QUESTION_RESPONSE = "Sure."
    YELLING_RESPONSE = "Woah, chill out!"
    EMPTY_RESPONSE = "Fine. Be that way!"
    DEFAULT_RESPONSE = "Whatever."

    def hey(self, message):
        """Return a reply to the passed message."""
        if message.isupper():
            return self.YELLING_RESPONSE
        elif message.endswith("?"):
            return self.QUESTION_RESPONSE
        elif len(message.strip()) == 0:
            return self.EMPTY_RESPONSE
        return self.DEFAULT_RESPONSE
