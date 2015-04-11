#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    RES_QUESTION = "Sure."
    RES_YELL = "Whoa, chill out!"
    RES_SILENCE = "Fine. Be that way!"
    RES_DEFAULT = "Whatever."
    
    try:
        if what.isupper():
            return RES_YELL
        elif what.strip()[-1:] == "?":
            return RES_QUESTION
        elif what.strip() == "":
            return RES_SILENCE
        else:
            return RES_DEFAULT

    except Error:
        return RES_DEFAULT
