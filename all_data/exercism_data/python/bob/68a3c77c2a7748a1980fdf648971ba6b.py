#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what.strip() == "":
        return "Fine. Be that way!"

    if what.upper() == what and any([c.isalpha() for c in what]):
        return "Whoa, chill out!"

    if what.strip()[-1] == '?':
        return "Sure."

    return "Whatever."