#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    what = what.strip()
    if _is_silence(what):
        return "Fine. Be that way!"
    if _is_yelling(what):
        return "Whoa, chill out!"
    if _is_question(what):
        return "Sure."

    return "Whatever."


def _is_silence(what):
    return what == ""


def _is_yelling(what):
    return what.upper == what and what.lower != what


def _is_question(what):
    return what.endswith('?')
