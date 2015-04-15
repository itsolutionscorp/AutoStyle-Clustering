#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what.isupper():
        return "Woah, chill out!"
    if what.endswith("?"):
        return "Sure."
    if what.strip() == "" or what is None:
        return "Fine. Be that way!"
    return "Whatever."
