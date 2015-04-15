import string

class Bob:
    def hey(self, message):
        if not message:
            return 'Fine. Be that way.'
        elif message.endswith('?'):
            return 'Sure.'
        elif message.isupper():
            return 'Woah, chill out!'
        else:
            return 'Whatever.'
