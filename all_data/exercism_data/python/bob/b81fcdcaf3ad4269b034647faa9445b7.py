#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    # Trailing whitespaces are not needed
    what = what.rstrip()

    # Answer to yelling
    if what.isupper():
        answer = 'Whoa, chill out!'

    # Answer to empty arg
    elif not what:
        answer = 'Fine. Be that way!'

    # Answer to questions
    elif what[-1] == '?':
        answer = 'Sure.'

    # Answer to anything else
    else:
        answer = 'Whatever.'

    return answer
