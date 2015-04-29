#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    what = what.strip()
    if what == u"Bob" or what.strip() == u"":
        return call_my_name()
    elif not (not what.isupper() and not what.islower()) and not what.islower():
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
