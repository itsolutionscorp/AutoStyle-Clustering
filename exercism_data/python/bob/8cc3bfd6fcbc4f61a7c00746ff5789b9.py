#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    cleanstr = what.replace(" ", "")
    cleanstr = cleanstr.replace("\t", "")
    if len(cleanstr) == 0:
        return "Fine. Be that way!"
    elif cleanstr.upper() == cleanstr and any(c.isalpha() for c in cleanstr):
        return "Whoa, chill out!"
    elif "?" in cleanstr[len(cleanstr) - 1]:
        return "Sure."
    else:
        return "Whatever."
