#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what.strip()
    #default answer
    answer = 'Whatever.'
    if what.isupper():
        answer = 'Whoa, chill out!'
    elif what.strip().endswith('?'):
        answer = 'Sure.'
    elif what == '':
        answer = 'Fine. Be that way!'

    return answer
