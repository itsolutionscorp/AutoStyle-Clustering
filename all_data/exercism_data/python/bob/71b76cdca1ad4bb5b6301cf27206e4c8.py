class Bob(object):
    """A lackadaisical teenager."""
    def hey(self, phrase):
        if len(phrase) == 0 or phrase.isspace():
            return 'Fine. Be that way!'
        elif phrase.isupper():
            return 'Woah, chill out!'
        elif phrase.endswith('?'):
            return 'Sure.'
        else:
            return 'Whatever.'
