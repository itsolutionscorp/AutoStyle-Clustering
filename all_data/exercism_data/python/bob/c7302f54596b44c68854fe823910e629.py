class Bob(object):
    def hey(self, msg):
        if msg.upper() == msg and [c for c in msg if c.isalpha()]:
            # All caps, but not all digits
            return u'Woah, chill out!'
        elif msg.endswith(u'?'):
            return u'Sure.'
        elif not msg.strip():
            return u'Fine. Be that way!'
        else:
            return u'Whatever.'
