#
# Skeleton file for the Python "Bob" exercise.
#


def is_shouting(phrase):
    """Check if the phrase represents someone screaming

    :phrase: a string object
    :returns: True or False

    """
    return phrase.strip() and phrase.isupper()


def is_asking(phrase):
    """Check if the phrase is a question

    :phrase: a string object
    :returns: True or False

    """
    return phrase.strip() and phrase.strip().endswith('?')


def hey(what):

    return (
        (is_shouting(what) and 'Whoa, chill out!')
        or
        (is_asking(what) and 'Sure.')
        or
        (what.strip() == '' and 'Fine. Be that way!')
        or 'Whatever.'
    )
