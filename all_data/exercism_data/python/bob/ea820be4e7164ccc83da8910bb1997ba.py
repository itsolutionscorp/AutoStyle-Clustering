#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    ## clear trailing whitespace
    what = what.strip()
    ## check for empty input
    if what == "":
        return "Fine. Be that way!"
    ## check for all-caps yelling (exclude cases with no letters)
    elif what.upper() == what and what.lower() != what:
        return "Whoa, chill out!"
    ## check for questions
    elif what.endswith("?"):
        return "Sure."
    ## default
    return "Whatever."
