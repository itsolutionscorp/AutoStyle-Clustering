#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    what = what.strip()
    if what.strip() == u"":
        return call_my_name()
    elif what.isupper():
        return yelling()
    elif what.endswith(u"?"):
        return question()
    else:
        return whatever()


def call_my_name():
    return u"Fine. Be that way!"


def whatever():
    return u"Whatever."


def question():
    return u"Sure."


def yelling():
    return u"Whoa, chill out!"
