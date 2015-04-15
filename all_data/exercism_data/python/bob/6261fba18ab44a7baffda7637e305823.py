import re

class Bob():
    def hey(self, ask):
        if not ask.strip():
            return 'Fine. Be that way!'
        elif ask == ask.upper() and re.search('[a-zA-Z]+', ask):
            return 'Woah, chill out!'
        elif ask[-1] == '?':
            return 'Sure.'
        else:
            return 'Whatever.'
