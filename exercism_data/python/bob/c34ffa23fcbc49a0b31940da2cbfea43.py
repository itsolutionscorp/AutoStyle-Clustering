#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    splitstring = what.split()
    strlen = len(splitstring)
    # If silent
    if len(what) == 0:
        return "Fine. Be that way!"
    # If yelling (upper > lower)
    if len([word for word in splitstring if word.isupper()]) > len([word for word in splitstring if word.islower()]):
        return 'Whoa, chill out!'
    # If asking
    if what[len(what)-1] == "?":
        return 'Sure.'
    # Else
    return "Whatever."
