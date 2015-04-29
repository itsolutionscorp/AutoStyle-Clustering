import re

class Bob:
        def hey(self, x):
                x = x.strip()
                if not x:
                        return 'Fine. Be that way!'
                if x.isupper():
                        return 'Woah, chill out!'
                elif x.endswith('?'):
                        return 'Sure.'
                else:
                        return 'Whatever.'
