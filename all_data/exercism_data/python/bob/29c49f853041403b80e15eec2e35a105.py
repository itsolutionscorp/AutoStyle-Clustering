'''Implementation of the 'bob' exercism.io assignment'''

import string

class Bob(object):
    '''Bob implementation'''

    # TODO Rework as a simple function, no need for classes
    @staticmethod
    def hey(s):
        '''Implementation of the 'hey' spec'''

        # No multi-exits
        result = None

        if s is None or all(c in string.whitespace for c in s):
            result = 'Fine. Be that way.'
        elif s == s.upper():
            result = 'Woah, chill out!'
        elif s[-1] == '?':
            result = 'Sure.'
        else:
            result = 'Whatever.'

        return result
