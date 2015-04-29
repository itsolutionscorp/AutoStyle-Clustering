# bob.py
import string

def hey(message):
    """ Responds with different messages based on input """
    # Check if message is capitalised (shouting)
    if message.isupper():
        return 'Whoa, chill out!'

    # Check if message is a question
    if message.endswith('?'):
        return 'Sure.'

    # Check if message is empty
    if is_empty(message):
        return 'Fine. Be that way!'

    # Runs if no conditions are met
    return 'Whatever.'

def is_empty(message):
    """
    Checks if there is anything meaningful in 'message'

    Returns True if 'message' consists of whitespace
    Returns False is there are non-whitespace characters in 'message'
    """
    # Takes all whitespace characters out of 'message'
    message = ''.join([char for char in message if char not in string.whitespace])

    # Convert 'message' to a boolean value and return it
    return not bool(message)
