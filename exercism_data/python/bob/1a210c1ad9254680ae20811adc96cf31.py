import re
#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    """
    Say hey to bob! Given any string this function returns an appropriate answer.
    """
    if len(what.strip()) == 0:
        return "Fine. Be that way!"
    elif what.upper() == what and re.compile("[A-Z]").search(what):
        return "Whoa, chill out!"
    elif what.strip().endswith('?'):
        return "Sure."
    else:
        return "Whatever."
