"""
Bob the teenager
"""


def hey(message):
    """
    Respond to 'hey'
    """
    if _is_empty(message):
        response = 'Fine. Be that way!'
    elif _is_yelling(message):
        response = 'Whoa, chill out!'
    elif _is_question(message):
        response = "Sure."
    else:
        response = 'Whatever.'

    return response


def _is_question(text):
    """
    Returns true if the text is a question
    """
    return text.endswith('?')


def _is_yelling(text):
    """
    Checks if text is shouted, all letters in upper case
    """
    return text.isupper()


def _is_empty(text):
    """
    Check for empty messages
    """
    return len(text.strip()) == 0
