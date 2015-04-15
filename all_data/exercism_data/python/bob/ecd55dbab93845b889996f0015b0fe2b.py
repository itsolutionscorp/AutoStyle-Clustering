#
# Skeleton file for the Python "Bob" exercise.
#
import string

def is_question(phrase):
    """ Check if phrase is a question.

        :param phrase: str
        :returns: Bool
    """
    try:
        return phrase.rstrip()[-1] == '?'
    except IndexError:
        return False


def is_nothing(phrase):
    """ Check if phrase says nothing

        :param phrase: str
        :returns: Bool
    """
    return phrase.rstrip() == ""


def is_shouting(phrase):
    """ Check if phrase is shouted

        :param phrase: str
        :returns: Bool
    """
    if any([char in string.ascii_letters for char in phrase]):
        return phrase.upper() == phrase
    else:
        return False


def hey(what):
    """ Bobs answer. """
    if is_shouting(what):
        return "Whoa, chill out!"
    elif is_question(what):
        return "Sure."
    elif is_nothing(what):
        return "Fine. Be that way!"
    else:
        return "Whatever."
