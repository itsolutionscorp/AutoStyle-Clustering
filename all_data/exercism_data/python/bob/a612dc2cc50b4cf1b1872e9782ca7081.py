import re
class Bob(object):
    """docstring for Bob"""
    def __init__(self):
        super(Bob, self).__init__()
    
    def hey(self, msg):
        if re.match('^\s*$', msg):
            return 'Fine. Be that way!'
        if re.match('^[^a-zA-Z]+$', msg):
            if msg.endswith('?'):
                return 'Sure.'
            return 'Whatever.'
        if msg.upper() == msg:
            return 'Woah, chill out!'
        if msg.endswith('?'):
            return 'Sure.'
        return 'Whatever.'
