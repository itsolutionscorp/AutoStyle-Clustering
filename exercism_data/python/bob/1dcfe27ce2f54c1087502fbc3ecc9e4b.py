class Bob:
    def hey(self, message):
        if not message:
            message = ' '
        if message.isspace():
            return 'Fine. Be that way.'
        if message == message.upper():
            return 'Woah, chill out!'
        if message[-1] == '?':
            return 'Sure.'
        return 'Whatever.'
