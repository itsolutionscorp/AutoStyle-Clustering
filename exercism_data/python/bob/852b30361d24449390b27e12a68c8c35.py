#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    if what == '' or  what.isalpha() == True or what.isspace() == True:
        return 'Fine. Be that way!'
    elif what.isupper():
        return 'Whoa, chill out!'
    elif what.split()[-1][-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
