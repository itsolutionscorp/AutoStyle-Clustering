import re
class Bob(object):
    """docstring for Bob"""
    def __init__(self):
        super(Bob, self).__init__()
    
    def hey(self, msg):
        if msg.isupper():
            return 'Woah, chill out!'
        elif re.match('^\s*$', msg):
            return 'Fine. Be that way!'
        elif msg.endswith('?'):
            return 'Sure.'
        else:
            return 'Whatever.'
