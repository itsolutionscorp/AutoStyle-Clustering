#
# Skeleton file for the Python "Bob" exercise.
#


def is_shouting(phrase):
    """TODO: Docstring for is_shouting.

    :phrase: TODO
    :returns: TODO

    """
    return phrase.strip() and phrase.isupper()


def is_asking(phrase):
    """TODO: Docstring for is_asking.

    :phrase: TODO
    :returns: TODO

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
