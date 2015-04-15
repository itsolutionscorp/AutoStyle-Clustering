class Bob:
    def hey(self, msg):
        if msg is None or not msg.strip():
            return 'Fine. Be that way!'
        if msg.isupper():
            return 'Woah, chill out!'
        if msg.strip().endswith('?'):
            return 'Sure.'
        return 'Whatever.'
