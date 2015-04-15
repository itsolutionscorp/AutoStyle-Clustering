#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    if what.isupper():
        # Yell at Bob
        return u"Whoa, chill out!"
    elif not len(what.strip()):
        # Don't saying anything
        return u"Fine. Be that way!"
    elif what.strip().rfind("?") == len(what.strip()) - 1:
        # Asking Bob a question
        return u"Sure."

    return u"Whatever."
