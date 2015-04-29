class Bob:
    def hey(self, say):
        if say:
            say = say.strip()
        if not say:
            return 'Fine. Be that way!'
        elif say.isupper():
            return 'Woah, chill out!'
        elif say.endswith('?'):
            return 'Sure.'
        else:
            return 'Whatever.'
