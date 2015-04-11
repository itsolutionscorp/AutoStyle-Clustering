class Bob():
    def hey(self, message):
        if not message:
            return 'Fine. Be that way!'
        if message.isupper():
            return 'Woah, chill out!'
        if message.endswith('?'):
            return 'Sure.'
        else:
            return 'Whatever.'
