class Message:
    """Represents a message"""

    def __init__(self, text):
        self.text = text

    def isSilence(self):
        return self.text == None or self.text.strip() == ''

    def isQuestion(self):
        return self.text.endswith("?")

    def isShouting(self):
        return self.text.isupper()


class Bob:
    """This is Bob. Bob is awesome."""

    def hey(self, text):
        message = Message(text)

        if message.isSilence():
            return "Fine. Be that way."
        elif message.isShouting():
            return "Woah, chill out!"
        elif message.isQuestion():
            return "Sure."
        else:
            return 'Whatever.'
