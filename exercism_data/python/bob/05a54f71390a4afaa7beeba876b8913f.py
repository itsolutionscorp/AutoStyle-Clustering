#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    """ When given a phrase, Bob must answer with the following:
        If the phrase is a question, answer "Sure."
        If the phrase is a statement, answer "Whatever."
        If the phrase is SHOUTING, answer "Whoah, chill out!"
    """

    what = what.strip()

    if what == None or what == "":
        return "Fine. Be that way!"

    if what.isupper():
        return "Whoa, chill out!"

    if what.endswith("?"):
        return "Sure."

    else:
        return "Whatever."
