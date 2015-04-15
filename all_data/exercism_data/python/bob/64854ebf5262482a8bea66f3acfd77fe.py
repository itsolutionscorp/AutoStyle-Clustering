import re

def hey(arg=None):
    """ bob says hi """
    if not re.findall('\S', arg):
        return 'Fine. Be that way!'
    elif arg.isupper():
        return 'Woah, chill out!'
    if arg[-1] == '?':
        return 'Sure.'
    return 'Whatever.'
