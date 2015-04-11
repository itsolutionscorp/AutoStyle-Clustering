#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    """
    This may be my Java background showing, but I found this to be more natural than using if, elif else
    Would using if, elif, else be more Pythonic?
    """
    if what == what.upper() and not only_numbers(what):
        return 'Whoa, chill out!'
    if what[-1:] == '?':
        return 'Sure.'
    if only_silence(what):
        return 'Fine. Be that way!'
    return 'Whatever.'

def only_numbers(string):
    """ 
    Finds out if the given string only consists of numbers, spaces or commas except from the punctuation mark.
    Not satisfied with the implementation, but it was the best I could come up with.
    """
    for character in string[:-1]:
        if not (character.isdigit() or character in (',', ' ')): 
            return False
    return True

def only_silence(string):
    """
    Checks if the string consists of only tabs, spaces or newlines.
    """
    for character in string:
        if not character in ('', ' ', '\t', '\n'):
            return False
    return True
