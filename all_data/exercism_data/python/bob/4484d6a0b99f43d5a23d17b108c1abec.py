import re
class Bob():
    def hey(self, input):
        if not input or input.isspace():
            return 'Fine. Be that way!'
        elif input.isupper():
            return 'Woah, chill out!'
        elif input.endswith('?'):
            return 'Sure.'
        else:
            return 'Whatever.'
