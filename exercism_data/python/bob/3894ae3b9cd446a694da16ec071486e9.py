class Bob:
    def hey(self, message):
        if message.endswith('?') and not message.isupper():
            return 'Sure.'
        if message.isupper():
            return 'Woah, chill out!'
        if not message or message.isspace():
            return 'Fine. Be that way!'
        return 'Whatever.'
