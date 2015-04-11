#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if len(what)==0:
        return "Fine. Be that way!"
    if (what.isupper()):
        return 'Woah, chill out!'
    if (what[-1] == '?'):
        return 'Sure.'
    said_sth = 0
    for i in what:
        if (i!=' ' and i!='\t' and i!='\n'):
            said_sth = 1
            break
    if not said_sth:
        return "Fine. Be that way!"
    else:
        return 'Whatever.'
