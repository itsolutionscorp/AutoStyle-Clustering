import string


def hey(what):
    """Get a response from bob.

    Args:
        what: A string containing a message said to bob.
    Returns:
        A string containing a response.
    """

    # Ignore starting or ending whitespace
    what = what.strip()

    # No message passed!
    if not what:
        return 'Fine. Be that way!'

    # Message is yelling
    elif what.upper() == what and any(x in string.uppercase for x in what):
        return 'Whoa, chill out!'

    # Message is a question
    elif what.endswith('?'):
        return 'Sure.'

    # Unrecognized message
    else:
        return 'Whatever.'
