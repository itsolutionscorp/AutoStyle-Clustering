import string

class Bob:
    def hey(self, message):
        if not message:
            return 'Fine. Be that way.'
        elif message[-1] == '?':
            return 'Sure.'
        elif all(ch not in string.ascii_lowercase for ch in message):
            return 'Woah, chill out!'
        else:
            return 'Whatever.'
