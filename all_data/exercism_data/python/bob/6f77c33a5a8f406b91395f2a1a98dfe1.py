import re

class Bob:
    def hey(self, message):
        message = message or ''

        if is_silence(message):
            return 'Fine. Be that way.'
        elif is_shouty(message):
            return 'Woah, chill out!'
        elif is_question(message):
            return 'Sure.'
        else:
            return 'Whatever.'

def is_silence(message):
    return re.compile(r"^\s*$").match(message)

def is_shouty(message):
    return re.compile(r"[A-Z]").search(message) and message.upper() == message

def is_question(message):
    return re.compile(r"\?$").search(message)
