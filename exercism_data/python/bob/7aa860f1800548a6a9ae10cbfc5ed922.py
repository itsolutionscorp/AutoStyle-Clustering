#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = " ".join(what.split())  # Takes care of any whitespace that might appear.
    if what.isupper() == True:  # Checks if Bob is being yelled at.
        return "Whoa, chill out!"
    elif len(what) == 0:
        return "Fine. Be that way!"
    elif what[-1] == '?':  # Checks if Bob is being asked a question
        return "Sure."
    else:
        return "Whatever."
