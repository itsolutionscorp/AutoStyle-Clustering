class Bob(object):
    def hey(self, msg):

        if not msg.strip():
            return 'Fine. Be that way!'

        if msg.upper() == msg and [c for c in msg if c.isalpha()]:
            return 'Woah, chill out!'

        if msg.endswith('?'):
            return 'Sure.'

        return 'Whatever.'
