import string

class Bob:
    def hey(self, message):
        if not message:
            return 'Fine. Be that way.'

        if message.endswith('?'):
            return 'Sure.'

        if message.isupper():
            return 'Woah, chill out!'

        return 'Whatever.'
