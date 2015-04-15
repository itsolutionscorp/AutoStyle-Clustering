class Bob(object):

    def hey(self, message):
        if not message:
            return 'Fine. Be that way.'
        elif message.upper() == message:
            return 'Woah, chill out!'
        elif message.endswith('?'):
            return 'Sure.'
        elif message.endswith('.'):
            return 'Whatever.'
        else:
            return "Sometimes no one tells me what to say."
