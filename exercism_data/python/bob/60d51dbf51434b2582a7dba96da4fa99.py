'''Implementation of the 'bob' exercism.io assignment'''

import string

is_empty = lambda s: s is None or s.strip() == ''
is_shout = lambda s: s == s.upper()
is_question = lambda s: s and s[-1] == '?'

class Bob(object):
    '''Bob implementation'''

    # TODO Rework as a simple function, no need for classes
    @staticmethod
    def hey(s):
        '''Implementation of the 'hey' spec'''

        # No multi-exits
        result = None

        if is_empty(s):
            result = 'Fine. Be that way.'
        elif is_shout(s):
            result = 'Woah, chill out!'
        elif is_question(s):
            result = 'Sure.'
        else:
            result = 'Whatever.'

        return result
