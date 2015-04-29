#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    line = what.strip()
    if (len(line) == 0):
        return "Fine. Be that way!"
    if (line.upper() == line and any(l.isalpha() for l in line)):
        return "Whoa, chill out!"
    if (line[-1] == "?"):
        return "Sure."
    return "Whatever."
