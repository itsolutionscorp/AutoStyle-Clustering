"""Bob the teenager"""


def is_shouting(phrase):
    """Returns true if all characters that have a case are upper case."""
    return phrase.isupper()


def is_question(phrase):
    """Returns true if the phrase is a question. It's assumed to be a question
    only if it ends in a question mark."""
    return phrase[-1] == '?'


def hey(phrase):
    """Addresses Bob with phrase."""
    # Start by removing potentially confusing whitespace.
    phrase = phrase.strip()
    # If there's nothing left then the input was silence.
    if len(phrase) == 0:
        return 'Fine. Be that way!'
    if is_shouting(phrase):
        return 'Whoa, chill out!'
    if is_question(phrase):
        return 'Sure.'
    return 'Whatever.'
