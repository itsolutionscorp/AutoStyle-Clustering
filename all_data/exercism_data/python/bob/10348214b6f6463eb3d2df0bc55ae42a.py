#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    response = 'Passed the checks'
    if what.upper() == what and has_alpha(what):
        response =  'Whoa, chill out!'
    elif len(what) == 0:
        response = 'Fine. Be that way!'
    elif what[-1] == '?':
        response = 'Sure.'
    else:
        response = 'Whatever.'
        
    return response
    
def has_alpha(s):
    return any(char.isalpha() for char in s)
