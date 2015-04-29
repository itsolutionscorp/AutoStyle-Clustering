#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what.isupper():
        return 'Whoa, chill out!'
    elif not what or what.isspace():
        return 'Fine. Be that way!'
    elif what[-1] == '?' or what[-1] == ' ':
        return 'Sure.'
    
    else:
        return 'Whatever.'
