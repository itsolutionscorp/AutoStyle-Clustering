class Bob():
    def hey(self, message):
        if self.isSilent(message):
            return 'Fine. Be that way!'
        if self.isAngry(message):
            return 'Woah, chill out!'
        if self.isQuestion(message):
            return 'Sure.'
        return 'Whatever.'

    def isSilent(self, message):
        return message is None or message.strip() == ''

    def isAngry(self, message):
        return message == message.upper()

    def isQuestion(self, message):
        return message.endswith('?')
