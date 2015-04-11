class Bob(object):
    def hey(self, phrase):
        if phrase.isupper():
            return 'Woah, chill out!'
        elif phrase.endswith('?'):
            return 'Sure.'
        elif not phrase.strip():
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'
