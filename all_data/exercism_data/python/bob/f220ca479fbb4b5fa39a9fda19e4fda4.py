import re

class Bob(object):
    def hey(self, msg):
        if not msg or not msg.strip():
            return 'Fine. Be that way!'
        elif not any(x.islower() for x in msg):
            return 'Woah, chill out!'
        elif msg.endswith('?'):
            return 'Sure.'
        return 'Whatever.'
