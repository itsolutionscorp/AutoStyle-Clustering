class Bob:
    def hey(self, text):
        text = text.strip()
        letters = [c for c in text if c.isalpha()]
        if not len(text):
            return 'Fine. Be that way!'
        elif len(letters) > 0 and text.isupper():
            return 'Woah, chill out!'
        elif text.endswith('?'):
            return 'Sure.'
        else:
            return 'Whatever.'
