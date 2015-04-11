class Bob():
    def hey(self, message):
        if self.is_silent(message):
            return 'Fine. Be that way!'
        if self.is_angry(message):
            return 'Woah, chill out!'
        if self.is_question(message):
            return 'Sure.'
        return 'Whatever.'

    def is_silent(self, message):
        return message is None or message.strip() == ''

    def is_angry(self, message):
        return message.isupper()

    def is_question(self, message):
        return message.endswith('?')
