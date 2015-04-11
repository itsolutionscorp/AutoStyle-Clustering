#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    if what.strip() == "":
        return "Fine. Be that way!"
    if what.upper() == what and what.lower() != what:
        return "Whoa, chill out!"
    if what.endswith('?'):
        return "Sure."

    return "Whatever."
