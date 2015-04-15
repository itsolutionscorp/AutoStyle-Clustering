#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    # Strip any leading or trailing whitespace.
    what = what.strip()

    # If you don't really say anything:
    if not what:
        return "Fine. Be that way!"

    # If you "yell" at Bob.
    # Yelling = there is at least one letter, and all letters are uppercase.
    if what.isupper():
        return "Whoa, chill out!"

    # If you ask Bob a question.
    # Based on the data, if you "yell" a question, the yelling takes
    # precedence.
    if what.endswith('?'):
        return "Sure."

    # Anything else.
    return "Whatever."
