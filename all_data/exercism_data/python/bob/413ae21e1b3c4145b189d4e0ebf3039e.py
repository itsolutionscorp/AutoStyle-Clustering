class Bob(object):
    def __init__(self):
        pass

    def hey(self, msg):
        if msg.isupper():
            return 'Woah, chill out!'
        elif len(msg) >= 1 and msg[-1] == '?':
            return 'Sure.'
        elif len(msg) == 0 or msg.isspace():
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'
