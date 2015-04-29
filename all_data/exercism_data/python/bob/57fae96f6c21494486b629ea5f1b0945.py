class Bob():
    def silence(self, message):
        if not message:
            return True
        else:
            return False
        
    def question(self, message):
        if message[-1] == '?':
            return True
        else:
            return False

    def shout(self, message):
        if message.isupper():
            return True
        else:
            return False

    def hey(self, message):
        if self.silence(message):
            return 'Fine. Be that way.'
        elif self.question(message):
            return 'Sure.'
        elif self.shout(message):
            return 'Woah, chill out!'
        else:
            return 'Whatever.'
