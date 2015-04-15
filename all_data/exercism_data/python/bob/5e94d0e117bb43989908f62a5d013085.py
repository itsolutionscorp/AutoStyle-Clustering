#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what_no_spaces = what.strip()

    if what_no_spaces.isupper():
        return "Whoa, chill out!"
    elif what_no_spaces.endswith("?"):
        return "Sure."
    elif not what_no_spaces:
        return "Fine. Be that way!"
    else:
        return "Whatever."
