#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    # According to the tests the "YELLING" case takes precedence over questions.
    # though the spec didn't specify that!
    if what.isupper():  # Yelling
        return "Whoa, chill out!"
    elif what[-1:] == "?":  # Questioning
        return "Sure."
    elif what.strip() == "":  # Silence
        return "Fine. Be that way!"
    else:  # Everything else
        return "Whatever."
