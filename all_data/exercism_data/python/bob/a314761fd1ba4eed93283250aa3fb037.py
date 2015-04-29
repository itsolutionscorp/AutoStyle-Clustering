#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    answer = 'Whatever.'
    which = what.strip()
    if which.isupper():
        answer = 'Whoa, chill out!'
    elif which.endswith('?'):
        answer = 'Sure.'
    elif not which:
        answer = 'Fine. Be that way!'
    return answer
