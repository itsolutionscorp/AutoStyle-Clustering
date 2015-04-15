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
    elif what.find("?",len(what)-2) >= 0:
        return questionResponse
    elif what.find("!",len(what)-2) >= 0 and what.isupper():
        return yellResponse
    else:
        return anythingResponse
