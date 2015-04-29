#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    l =[]

    if len(what)==0:
        return 'Fine. Be that way!'
    elif '.' in what:
        return 'Whatever.'
    elif '!' in what:
        if what.upper() == what:
            return 'Whoa, chill out!'
        else:
            return 'Whatever.'
    elif '?' in what:
        if what.upper() == what:
            return 'Whoa, chill out!'
        else:
            return 'Sure.'
    elif what.isspace():
        return 'Fine. Be that way!'
    else:

        return 'Whatever.'
