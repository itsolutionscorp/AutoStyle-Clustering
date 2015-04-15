#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    if what is None or what.strip() == '':
        return 'Fine. Be that way!'
    elif what.replace(" ","")[-1] == "?" and not what.isupper():
        return 'Sure.'
    elif what.isupper():
        return 'Whoa, chill out!'
    else:
        return 'Whatever.'
