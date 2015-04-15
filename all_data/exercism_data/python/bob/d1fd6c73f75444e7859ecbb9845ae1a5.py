#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if (any(map(str.isalpha, what)) and what == what.upper()):
        return ("Whoa, chill out!")
    elif (len(what.strip()) >= 1 and what.strip()[-1] == '?'):
        return ("Sure.")
    elif (what.strip() == ""):
        return ("Fine. Be that way!")
    return ("Whatever.")
