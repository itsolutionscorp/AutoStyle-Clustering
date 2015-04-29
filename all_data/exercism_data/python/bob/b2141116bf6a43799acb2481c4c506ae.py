class Bob():

    def __init__(self):
        pass

    def hey(self, message):
        if(not message or message.strip() == ''):
            return 'Fine. Be that way!'
        if(message.isupper()):
            return 'Woah, chill out!'
        if(message.endswith('?')):
            return 'Sure.'

        else:
            return 'Whatever.'
