class Bob(object):
    def __init__(self):
        pass

    def hey(self, msg):
        if msg.isupper():
            return 'Woah, chill out!'
        elif msg.endswith('?'):
            return 'Sure.'
        elif not msg.strip():
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'
