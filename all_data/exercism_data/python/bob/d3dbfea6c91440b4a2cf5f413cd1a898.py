class Bob():

    def __init__(self):
        pass

    def hey(self, message):
        if not message or not message.strip():
            return 'Fine. Be that way!'
        elif message.isupper():
            return 'Woah, chill out!'
        elif message.endswith('?'):
            return 'Sure.'
        else:
            return 'Whatever.'
