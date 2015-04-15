class Bob():
    def __init__(self):
        pass

    @staticmethod
    def hey(text='', *args, **kwargs):
        if text.isupper():
            return 'Woah, chill out!'
        elif text == '' or text.isspace():
            return 'Fine. Be that way!'
        elif text[-1] == '?':
            return 'Sure.'
        else:
            return 'Whatever.'
