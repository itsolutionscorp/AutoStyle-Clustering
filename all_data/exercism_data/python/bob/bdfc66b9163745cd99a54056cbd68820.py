class Bob(object):
    def hey(self, message):
        if message is None or not message.strip():
            return 'Fine. Be that way!'
        elif message.isupper():
            return 'Woah, chill out!'
        elif message.endswith('?'):
            return 'Sure.'
        else:
            return 'Whatever.'
