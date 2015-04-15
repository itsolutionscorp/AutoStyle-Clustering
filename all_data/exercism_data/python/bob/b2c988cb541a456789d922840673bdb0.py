#
# Skeleton file for the Python "Bob" exercise.
#

import re


class Bob(object):

    def hey(self, what):
        if all(char == ' ' for char in list(what)):
            return 'Fine. Be that way!'
        elif all(letter == letter.upper() and not letter.isdigit() for letter in list(what)) or (all(letter == letter.upper() for letter in list(what)) and what.endswith('!')):
            return 'Woah, chill out!'
        elif what.endswith('?'):
            return 'Sure.'
        else:
            return 'Whatever.'
