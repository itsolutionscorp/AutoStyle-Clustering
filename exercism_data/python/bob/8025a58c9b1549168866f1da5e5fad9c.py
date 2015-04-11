class Bob:
    def hey(self, message):
        if self.is_empty_string(message):
            return 'Fine. Be that way.'
        elif self.is_shouting(message):
            return 'Woah, chill out!'
        elif self.is_question(message):
            return 'Sure.'
        else:
            return 'Whatever.'

    def is_empty_string(self, message):
        return message == ''

    def is_shouting(self, message):
        return message.upper() == message

    def is_question(self, message):
        return message.endswith('?')
