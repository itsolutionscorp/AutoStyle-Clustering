class Bob:
    def hey(self, message = ''):
        if is_silence(message):
            return 'Fine. Be that way.'
        elif is_shouty(message):
            return 'Woah, chill out!'
        elif is_question(message):
            return 'Sure.'
        else:
            return 'Whatever.'

def is_silence(message):
    return message is None or message.strip() == ""

def is_shouty(message):
    return message.isupper()

def is_question(message):
    return message.endswith("?")
