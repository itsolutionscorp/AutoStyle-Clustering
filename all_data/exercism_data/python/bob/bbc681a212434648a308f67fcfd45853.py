from __future__ import unicode_literals

class Bob(object):
    def hey(self, msg):
        msg = msg.strip()
        if msg.isupper():
            return 'Woah, chill out!'
        elif msg.endswith('?'):
            return 'Sure.'
        elif not msg:
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'
