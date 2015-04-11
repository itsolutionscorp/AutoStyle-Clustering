#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    input = what.strip()
    if (not input):
        return "Fine. Be that way!"
    if (input.isupper()):
        return "Whoa, chill out!"
    if (input.endswith("?")):
        return "Sure."
    return "Whatever."
