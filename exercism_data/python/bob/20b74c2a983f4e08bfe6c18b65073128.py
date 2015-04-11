"""exercism.io python bob exercise solution."""

def hey(_str):
    """Returns Bob's responses to input (_str)"""
    # If None or empty string
    if not _str or _str.isspace():
        return 'Fine. Be that way!'

    # If yell
    elif _str.isupper():
        return 'Woah, chill out!'

    # If question
    elif _str.endswith('?'):
        return 'Sure.'

    # Default
    return 'Whatever.'
