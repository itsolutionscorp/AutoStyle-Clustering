#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    if what.rstrip() == '':
        return 'Fine. Be that way!'
        
    if what.isupper():
        return 'Whoa, chill out!' 

    if what.rstrip()[-1] == '?':
        return 'Sure.'

    return 'Whatever.'
