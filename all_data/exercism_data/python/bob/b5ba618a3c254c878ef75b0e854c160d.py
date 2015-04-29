class Bob:
    def hey(self, msg):
        msg = msg and msg.strip()
        if not msg:
            return 'Fine. Be that way!'
        if msg.isupper():
            return 'Woah, chill out!'
        if msg.endswith('?'):
            return 'Sure.'
        return 'Whatever.'
