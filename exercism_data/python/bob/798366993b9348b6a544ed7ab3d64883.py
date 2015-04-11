#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what2 = what.strip()
    count = len(what2)
    if what2.isupper():
        return 'Whoa, chill out!'
    if count == 0:
        return 'Fine. Be that way!'
    elif what2[count - 1] == "?":
        return 'Sure.'
    else:
        return 'Whatever.'
