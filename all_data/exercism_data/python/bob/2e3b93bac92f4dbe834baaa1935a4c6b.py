import re

class Bob:
    def hey(self, input):
        if self.silence(input):
            return 'Fine. Be that way!'
        elif self.shouting(input):
            return 'Woah, chill out!'
        elif self.questioning(input):
            return 'Sure.'
        else:
            return 'Whatever.'

    def silence(self, input):
        return input.strip() == ''

    def shouting(self, input):
        if re.search('[a-zA-Z]+', input) is None:
            return False
        else:
            return input.upper() == input

    def questioning(self, input):
        return input.endswith('?')
