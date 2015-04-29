"""Module containing hey, a function to determine how Bob responds"""
def _is_yelling(what):
    """
    Determins whether the input is yelling (all caps)
    """
    return what.isupper()

def _is_question(what):
    """
    Determine whether the input is a question (ends in a ?)
    """
    return what.endswith('?')

def _is_empty(what):
    """
    Determines whether the string is empty or only contains whitespace
    """
    return what.isspace() or not what

def hey(what):
    """
    Hey determines how Bob responds to input.
    See README.md for more details on how bob responds

    Parameters:
        what (string): The input given to Bob

    Returns:
        Bob's response to the input
    """
    if _is_empty(what):
        return 'Fine. Be that way!'
    elif _is_yelling(what):
        return 'Whoa, chill out!'
    elif _is_question(what):
        return 'Sure.'
    else:
        return 'Whatever.'
