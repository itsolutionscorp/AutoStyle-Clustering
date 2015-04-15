#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    ## clear trailing whitespace
    what = what.strip()
    ## check for empty input
    if not what:
        return "Fine. Be that way!"
    ## check for all-caps yelling (exclude cases with no letters)
    elif what.isupper():
        return "Whoa, chill out!"
    ## check for questions
    elif what.endswith("?"):
        return "Sure."
    ## default
    return "Whatever."
