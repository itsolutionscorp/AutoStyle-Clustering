# -*- encoding: utf-8 -*-
#
# Skeleton file for the Python "Bob" exercise.
#
"""Bob exercise """

def hey(what):
    """Bob will reply """
    if what.isupper():
        return 'Whoa, chill out!'
    elif what.rstrip().endswith('?'):
        return 'Sure.'
    elif what.isspace() or what == '':
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
