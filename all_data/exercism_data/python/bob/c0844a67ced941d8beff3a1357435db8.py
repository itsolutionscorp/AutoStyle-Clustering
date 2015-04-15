#
# Skeleton file for the Python "Bob" exercise.
#


def preprocess(s):
    return s.strip()


def all_upper(s):
    return s.upper() == s and any(c.isalpha() for c in s)


def classify(what):
    if not what:
        return "Fine. Be that way!"
    elif all_upper(what):
        return "Whoa, chill out!"
    elif what[-1:] == "?":
        return "Sure."
    else:
        return "Whatever."


def hey(what):
    return classify(preprocess(what))
