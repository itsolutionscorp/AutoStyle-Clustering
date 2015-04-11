class Bob:

    def hey(self, phrase):
        if not phrase or phrase.isspace():
            return 'Fine. Be that way!'
        elif phrase.isupper():
            return 'Woah, chill out!'
        elif phrase[-1] == '?':
            return 'Sure.'
        else:
            return 'Whatever.'
