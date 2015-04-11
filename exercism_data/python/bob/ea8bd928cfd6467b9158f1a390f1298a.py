class Message:
    'Represents a message'

    def __init__(self, text):
        self.text = text

    def isEmpty(self):
        return self.text == None or self.text.strip() == ''

    def isQuestion(self):
        return self.text.endswith("?")

    def isShouting(self):
        return self.text.isupper()


class Bob:
    'This is Bob. Bob is awesome.'

    def hey(self, text):
        self.message = Message(text)

        if self.message.isEmpty():
            return "Fine. Be that way."
        elif self.message.isShouting():
            return "Woah, chill out!"
        elif self.message.isQuestion():
            return "Sure."
        else:
            return 'Whatever.'
