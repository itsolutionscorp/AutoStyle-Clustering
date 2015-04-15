# Python "Bob" exercise.
# @author: demipenguin

def hey(what):
    """Return what Bob would answer"""
    # remove extra whitespaces
    what = what.strip()

    response = "Whatever."
    if what == "":
        response = "Fine. Be that way!"
    elif what.isupper():
        response = "Whoa, chill out!"
    elif what.endswith("?"):
        response = "Sure."

    return response
