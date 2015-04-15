class Bob():

    def __init__(self):
        pass

    def hey(self, text):
        if text.endswith('?') and not text.isupper():
            return 'Sure.'
        elif text.isupper():
            return 'Woah, chill out!'
        elif len(text) == 0 or '  ' in text:
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'
