class Bob():
    def hey(self, message):
        if not message:
            return 'Fine. Be that way!'
        if (message == message.upper()):
            return 'Woah, chill out!'
        if (message[-1]) == '?':
            return 'Sure.'
        else:
            return 'Whatever.'
