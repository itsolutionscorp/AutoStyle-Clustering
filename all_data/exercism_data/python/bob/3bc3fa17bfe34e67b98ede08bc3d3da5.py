"""
bob: the module

"""


def _is_yelling(text):
    """
    Check if text is considered to be "yelling"

    :param text:
    :return:
    """
    # If there are no letters in text,
    # it's not considered yelling.
    if 0 == _get_alpha_count(text):
        return False

    # Test if chars are ALL CAPS
    for ch in text:
        if ch.isalpha() and not ch.isupper():
            return False

    return True


def _get_alpha_count(text):
    """
    Get the letter count in text

    :param text:
    :return:
    """
    return len([ch for ch in text if ch.isalpha()])


def hey(text):
    """
    "Hey" call

    :type text: str
    """
    if _is_yelling(text):
        return 'Whoa, chill out!'
    elif text.endswith('?'):
        return 'Sure.'
    elif '' == text.strip():
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
