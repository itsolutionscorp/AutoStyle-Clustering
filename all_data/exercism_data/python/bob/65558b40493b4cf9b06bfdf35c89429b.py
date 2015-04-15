import re

def hey(arg=None):
    """ bob says hi """
    if not arg.split():
        return 'Fine. Be that way!'
    elif arg.isupper():
        return 'Woah, chill out!'
    if arg.endswith('?'):
        return 'Sure.'
    return 'Whatever.'
