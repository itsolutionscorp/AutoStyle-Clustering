''' The Bob library of functions. '''
import string


def hey(what):
    '''  This function takes as input a string, and returns Bob's response '''

# We want the Whatever. string to be the default
    result = 'Whatever.'
    yelling = False
    what_caps = what.upper()
# If the input is not a str then we return the default
    if type(what) != str:
        return result
# First we need to check something is being said.
    if len(what.strip()) == 0:
        result = 'Fine. Be that way!'
# We can tell someone is yelling if all alphabetic chars are ALL CAPS
    elif what == what_caps:
        for char in what_caps:
            if char in string.ascii_uppercase:
                yelling = True
                break
        if yelling:
            result = 'Whoa, chill out!'
# We can assume any thing that ends with a ? is a question
    if what.endswith('?') and not yelling:
        result = 'Sure.'

    return result
