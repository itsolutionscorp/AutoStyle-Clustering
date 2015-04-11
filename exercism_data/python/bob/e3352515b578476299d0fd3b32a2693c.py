#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    answer = 'Whatever.'
    if what.isupper():
        answer = 'Whoa, chill out!'
    elif what[-1:] == '?':
        answer = 'Sure.'
    elif len(what.split()) == 0:
        answer = 'Fine. Be that way!'

    return answer
