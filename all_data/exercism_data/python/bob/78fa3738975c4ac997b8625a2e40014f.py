#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):

    what = what.replace(" ", "")
    newString = what.lower()

    if newString == "" or newString.endswith("\t"):
        answer = 'Fine. Be that way!'
    elif newString.endswith("?") and not what.isupper():
        answer = 'Sure.'
    elif what.isupper():
        answer = 'Whoa, chill out!'
    else:
        answer = 'Whatever.'
    return answer


hey('sam?')
