#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    # remove any leading/trailing whitespace
    what = what.lstrip().rstrip()

    # variable for our return string   
    answer = ''

    # conditionals to find out what was said

    # empty statement
    if what == '':
        answer = 'Fine. Be that way!'
    # check if uppercase or uppercase with exclamation point
    elif what.isupper() or (what.isupper() and what[-1] == '!'):
        answer = 'Whoa, chill out!'
    # check if last charachter is question mark
    elif what[-1] == '?':
        answer = 'Sure.'
    else:
        answer = 'Whatever.'
    return answer
