#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    # If you don't say anythin
    if not what or not what.strip():
        return 'Fine. Be that way!'
    else:
        # If you insults him
        if what.isupper():
            return "Whoa, chill out!"
        # if you ask him a question
        elif what[-1] == "?":
            return "Sure."
        # else
        else:
            return "Whatever."
