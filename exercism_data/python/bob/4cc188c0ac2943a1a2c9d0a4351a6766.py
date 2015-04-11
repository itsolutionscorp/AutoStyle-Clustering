class Bob:

    def hey(self, word):
        if (word is None) or (len(word.strip()) == 0):
            return 'Fine. Be that way!'
        elif word == word.upper():
            return 'Woah, chill out!'
        elif word[-1] == '?':
            return 'Sure.'
        return 'Whatever.'
