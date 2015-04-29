class TalkBot():

    def hey(self, message):
        message = message.strip()

        if message:
            if message.isupper():
                return 'Whoa, chill out!'
            elif message[-1] is '?':
                return 'Sure.'
            else:
                return 'Whatever.'
        else:
            return 'Fine. Be that way!'

bob = TalkBot()
