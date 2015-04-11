class Bob(object):
    def hey(self, message):
        if message is None or not message.strip():
            return 'Fine. Be that way!'
        elif (any(c.isupper() for c in message) and
                not any(c.islower() for c in message)):
            return 'Woah, chill out!'
        elif message.endswith('?'):
            return 'Sure.'
        else:
            return 'Whatever.'
