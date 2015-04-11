class Bob(object):
    def hey(self, phrase):
        if phrase.strip() == '':
            return 'Fine. Be that way!'
        elif phrase.isupper():
            return 'Woah, chill out!'
        if phrase.endswith('?'):
            return 'Sure.'
        else:
            return 'Whatever.'
