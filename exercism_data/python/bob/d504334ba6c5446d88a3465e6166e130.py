class Bob:
    def hey(self, speech):
        if speech.isupper():
            return 'Woah, chill out!'
        elif speech.endswith('?'):
            return 'Sure.'
        elif speech.strip() == '':
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'
