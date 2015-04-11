class Bob(object):
    def hey(self, phrase):
        if phrase.isupper():  # yelling
            return 'Woah, chill out!'
        elif phrase.endswith('?'):  # question
            return 'Sure.'
        elif not phrase.strip():  # silent
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'
