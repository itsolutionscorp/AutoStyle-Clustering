import re

class Bob:
    def hey(self, message):
        if self.is_silence(message):
            return 'Fine. Be that way.'
        elif self.is_shouty(message):
            return 'Woah, chill out!'
        elif self.is_question(message):
            return 'Sure.'
        else:
            return 'Whatever.'

    def is_silence(self, message):
        return re.compile(r"^\s*$").match(message)

    def is_shouty(self, message):
        return message.upper() == message

    def is_question(self, message):
        return re.compile(r"\?$").search(message)
