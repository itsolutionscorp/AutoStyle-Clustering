#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.rstrip()
    what = what.lstrip()
    if what.isupper():
        answer = 'Whoa, chill out!'
    elif what[-1:] == '?':
        answer = 'Sure.'
    elif what == '':
        answer = 'Fine. Be that way!'            
    else:
        answer = 'Whatever.'     
    return answer
