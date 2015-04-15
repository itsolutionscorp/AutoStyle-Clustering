"""exercism.io python bob exercise solution."""

def hey(_str):
    """Returns Bob's responses to input (_str)"""
    if not _str or _str.isspace():
        return 'Fine. Be that way!'

    elif _str.isupper():
        return 'Woah, chill out!'

    elif _str.find('?', -1) != -1:
        return 'Sure.'

    else:
        return 'Whatever.'
