#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    if _is_yelling(what):
        return "Whoa, chill out!"

    if _is_question(what):
        return "Sure."

    if _silent_treatment(what):
        return "Fine. Be that way!"

    return "Whatever."

def _silent_treatment(what):
    return not bool(what.strip())

def _is_question(what):
    return what.strip()[-1:] == "?"


def _is_yelling(what):
    return what.strip().isupper()
