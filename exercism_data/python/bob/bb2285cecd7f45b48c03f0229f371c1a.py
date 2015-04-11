#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if ((len(what)==0) | (what.isspace()==True)):
        return 'Fine. Be that way!'
    if what.isupper()==True:
        return 'Whoa, chill out!'
    if what[-1]=='?':
        return 'Sure.'
    return 'Whatever.'
