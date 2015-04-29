#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    what = what.strip()

    if len(what) == 0:
        return "Fine. Be that way!"

    ## Check if they're shouting
    ##   Include the second condition to exclude purely numerical
    ##   sentences, which should NOT count as shouting
    if what == what.upper() and not what == what.lower():
        return "Whoa, chill out!"

    if what[-1] == "?":
        return "Sure."

    return "Whatever."

    
