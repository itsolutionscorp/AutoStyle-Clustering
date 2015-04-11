#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    
    stripped = what.strip()
    
    if not stripped:
        return 'Fine. Be that way!'
    elif stripped.isupper():
        return 'Whoa, chill out!'
    elif stripped[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
