#
# Skeleton file for the Python u"Bob" exercise.
#
def hey(what):
    what = what.strip()

    is_yelling = lambda: what.isupper()
    is_question = lambda: what.endswith("?")
    is_nothing = lambda: len(what) == 0

    if is_yelling():
        return "Whoa, chill out!"
    elif is_question():
        return "Sure."
    elif is_nothing():
        return "Fine. Be that way!"
    else:
        return "Whatever."
