#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what.isupper():
        return 'Whoa, chill out!'
        
    elif len(what) > 0 and what[-1] == '?':
        return 'Sure.'
        
    elif not what.strip():
        return 'Fine. Be that way!'
    
    return 'Whatever.'
