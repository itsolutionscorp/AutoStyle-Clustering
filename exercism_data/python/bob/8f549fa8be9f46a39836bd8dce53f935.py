class Bob(object):
    def hey(self, message):
        if self.is_silent(message):
            return 'Fine. Be that way!'
        elif self.is_yell(message):
            return 'Woah, chill out!'
        elif self.is_question(message):
            return 'Sure.'
        else:
            return 'Whatever.'

    def is_question(self, message):
        return (len(message) > 0) and message[-1] == '?'

    def is_yell(self, message):
        return (message == message.upper()) and (message != message.lower())
    
    def is_silent(self, message):
        return not message.strip()
