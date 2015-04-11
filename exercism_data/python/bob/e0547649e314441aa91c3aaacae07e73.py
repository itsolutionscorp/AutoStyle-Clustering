#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    anythingResponse = "Whatever."
    questionResponse = "Sure."
    yellResponse = "Whoa, chill out!"
    nothingResponse = "Fine. Be that way!"

    if what.isspace() or what == None or what == "":
        return nothingResponse

    what = what.strip()

    if what.isupper():
        return yellResponse
    elif what.endswith("?"):
        return questionResponse
    elif what.endswith("!") and what.isupper():
        return yellResponse
    else:
        return anythingResponse
