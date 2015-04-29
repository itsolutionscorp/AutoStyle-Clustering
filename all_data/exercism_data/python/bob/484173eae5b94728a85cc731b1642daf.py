class Bob(object):
    def hey(self, message):
        message = message.strip()
        if len(message) == 0: # address Bob, without actually saying anything
            return 'Fine. Be that way!'
        elif message.isupper():
            return 'Woah, chill out!'
        elif message[-1] == '?': # Question
            return 'Sure.'
        else:
            return 'Whatever.'
