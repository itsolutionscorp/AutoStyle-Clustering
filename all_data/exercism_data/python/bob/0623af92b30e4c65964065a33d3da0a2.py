class Bob:

    def hey(self, phrase):
        if (not isinstance(phrase, str)) or len(phrase) == 0 or phrase.isspace():
            return 'Fine. Be that way!'

        if phrase.isupper():
            return 'Woah, chill out!'

        if phrase.endswith('?'):
            return 'Sure.'

        return 'Whatever.'
