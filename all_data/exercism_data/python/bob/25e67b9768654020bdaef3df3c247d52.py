class Bob:
    def hey(self, text):
        text = text.strip()
        letters = [c for c in text if c.isalpha()]
        if not text:
            return 'Fine. Be that way!'
        elif text.isupper():
            return 'Woah, chill out!'
        elif text.endswith('?'):
            return 'Sure.'
        else:
            return 'Whatever.'
