#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    
    what = what.replace(" ", "").strip()
    
    if what == "":
        return "Fine. Be that way!"
    elif what.isupper() or (what.upper() == what and what[-1] == "!"):
        #passes for input like (1, 2, 3!)
        return "Whoa, chill out!"
    elif what[-1] == "?":
        return "Sure."
    else:
        return "Whatever."
