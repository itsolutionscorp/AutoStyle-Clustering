class Bob:
    def hey(self, message):
        if message == '':
            return 'Fine. Be that way.'
        elif message.upper() == message:
            return 'Woah, chill out!'
        elif message.endswith('?'):
            return 'Sure.'
        else:
            return 'Whatever.'
