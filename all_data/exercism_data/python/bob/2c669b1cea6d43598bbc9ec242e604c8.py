class Bob(object):

    def hey(self, message=''):
        if not message or not message.strip():
            return 'Fine. Be that way.'
        elif message.isupper():
            return 'Woah, chill out!'
        elif message[-1] == '?':
            return 'Sure.'
        else:
            return 'Whatever.'
