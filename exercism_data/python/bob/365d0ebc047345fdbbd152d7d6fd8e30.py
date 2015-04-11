import string

def isAllUpper(what):
    """Returns true if what contains at least one letter and all
    letters are in caps"""

    if not any([letter.isupper() for letter in what]):
        return False
    
    return what.upper() == what # all caps


def hey(what):
    what = what.strip() # remove trailing whitespaces
    
    if len(what) == 0:
        return 'Fine. Be that way!'

    if isAllUpper(what):
        return 'Whoa, chill out!'

    if what[-1] == '?':
        return 'Sure.'
    
    return 'Whatever.'
