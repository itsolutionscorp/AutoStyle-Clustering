class Bob():

    def __init__(self):
        pass;

    def hey(self, msg):
        if(msg.isupper()):
            return 'Woah, chill out!'
        elif(msg[-1:]=='?'):
            return 'Sure.'
        elif(msg.isspace() or msg==''):
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'
