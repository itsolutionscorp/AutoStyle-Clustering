class Bob:
    def hey(self, text):
        text = text.strip()

        if text.isupper():
            return 'Woah, chill out!'
        elif text == '':
            return 'Fine. Be that way!'
        elif text[-1] == '?':
            return 'Sure.'
        else:
            return 'Whatever.'
