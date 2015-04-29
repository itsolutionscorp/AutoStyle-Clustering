class Bob():
    def hey(self, text):
        if text.strip() == '':
            return 'Fine. Be that way.'
        elif text.strip() == text.strip().upper():
            return 'Woah, chill out!'
        elif text.strip()[-1:] == '?':
            return 'Sure.'
        else:
            return 'Whatever.'
