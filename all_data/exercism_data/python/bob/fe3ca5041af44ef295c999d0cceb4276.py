class Bob:
    def hey(self, phrase):
        if phrase.isupper():
            return 'Whoa, chill out!'
        elif phrase.endswith('?'):
            return 'Sure.'
        elif phrase.isspace() or not phrase.strip():
            return 'Fine. Be that way!'
        return 'Whatever.'
