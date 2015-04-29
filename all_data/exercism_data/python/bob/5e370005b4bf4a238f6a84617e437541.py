# -*- coding: utf-8 -*-

def hey(bwords):
    """ (str) -> str

    Returns what bob would say as responses to phrase types

    >>>Bob answers 'Sure.' if you ask him a question.
    >>>He answers 'Whoa, chill out!' if you yell at him.
    >>>He says 'Fine. Be that way!' if you address him without actually saying
    anything.
    >>>He answers 'Whatever.' to anything else.
    """

    if bwords.isupper():
        return "Whoa, chill out!"
    elif bwords.endswith("?"):
        return "Sure."
    elif bwords == "" or bwords.isspace():
        return "Fine. Be that way!"
    else:
        return "Whatever."
