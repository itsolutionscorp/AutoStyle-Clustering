class Bob:
    def __init__(self):
        pass

    def hey(self, msg):
        if msg == None or msg.strip() == '':
            return 'Fine. Be that way!'

        if str.isupper(msg):
            return 'Woah, chill out!'

        if msg[-1] == '?':
            return 'Sure.'

        else:
            return 'Whatever.'
