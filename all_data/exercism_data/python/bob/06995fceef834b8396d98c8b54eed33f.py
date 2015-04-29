class Bob(object):
    def hey(self, message):
        if message.strip() == '':
            return 'Fine. Be that way!'
        if message.upper() == message and message.lower() != message:
            return 'Woah, chill out!'
        if message.endswith('?'):
            return 'Sure.'
        else:
            return 'Whatever.'
