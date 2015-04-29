class Bob():
    def silence(self, message):
        return not message
        
    def question(self, message):
        return message[-1] == '?'

    def shout(self, message):
        return message.isupper()

    def hey(self, message):
        if self.silence(message):
            return 'Fine. Be that way.'
        elif self.question(message):
            return 'Sure.'
        elif self.shout(message):
            return 'Woah, chill out!'
        else:
            return 'Whatever.'
