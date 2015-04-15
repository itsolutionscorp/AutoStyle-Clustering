#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    if what == "" :
        return "Fine. Be that way!"
    elif (what[-2].isalpha() or what[0].isalpha()) and (what == what.upper()):
        return "Whoa, chill out!"
    elif what[-1] == "?":
        return "Sure."
    else:
        return "Whatever."
    return
