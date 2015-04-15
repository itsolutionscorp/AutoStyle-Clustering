class Bob:
    def hey(self, message):
        if self.silence(message or ''):
            return 'Fine, be that way.'
        elif self.exclamation(message):
            return 'Woah, chill out!'
        elif self.question(message):
            return 'Sure.'
        else:
            return 'Whatever.'

    def silence(self, message):
        return message == ''
    def exclamation(self, message):
        return message == message.upper()
    def question(self, message):
        return message[-1] == '?'
