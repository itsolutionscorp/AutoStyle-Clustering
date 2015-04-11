''' The Bob library of functions. '''
import string


def hey(what):
    '''This function takes as input a string, and returns Bob's response'''

    if type(what) != str:
        return 'Whatever.'
    if len(what.strip()) == 0:
        return 'Fine. Be that way!'
    yelling = what.isupper()
    if yelling:
        return 'Whoa, chill out!'
    if what.endswith('?'):
        return 'Sure.'

    return 'Whatever.'
