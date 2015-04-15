class Bob(object):
    def hey(self, msg=''):
        if msg.isupper():
            return 'Woah, chill out!'
        elif msg.endswith('?'):
            return 'Sure.'
        elif msg.strip() is '':
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'
