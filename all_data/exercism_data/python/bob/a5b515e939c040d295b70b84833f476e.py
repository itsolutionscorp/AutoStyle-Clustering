class Bob(object):
    def hey(self, message):
        if not message or message.isspace():
            return 'Fine. Be that way!'
        if message.isupper():
            return 'Woah, chill out!'
        if message.endswith('?'):
            return 'Sure.'
        return 'Whatever.'