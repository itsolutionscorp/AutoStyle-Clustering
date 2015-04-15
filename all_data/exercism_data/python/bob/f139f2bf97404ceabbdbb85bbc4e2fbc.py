class Bob:
    def hey(self, text):
        text = text.strip()
        letters = [c for c in text if c.isalpha()]
        if len(text) == 0:
            return 'Fine. Be that way!'
        elif len(letters) > 0 and all(c.isupper() for c in letters):
            return 'Woah, chill out!'
        elif text[-1] == '?':
            return 'Sure.'
        else:
            return 'Whatever.'
