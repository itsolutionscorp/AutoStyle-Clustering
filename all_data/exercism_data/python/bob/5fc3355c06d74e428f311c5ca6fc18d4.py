class Bob(object):
    def hey_(self, msg):
        if msg.isupper():
            return 'Woah, chill out!'
        elif msg.endswith('?'):
            return 'Sure.'
        elif not msg.strip():
            return 'Fine. Be that way!'
        return 'Whatever.'

    def hey(self, msg):
        ret = self.hey_(msg)
        return ret
