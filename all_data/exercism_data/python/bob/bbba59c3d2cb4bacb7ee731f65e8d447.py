#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    
    stripped = what.strip()
    
    helper = [x.isupper() for x in stripped if x.isalpha()]
    
    if not stripped:
        return 'Fine. Be that way!'
    elif helper and all(helper):
        return 'Whoa, chill out!'
    elif stripped[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
