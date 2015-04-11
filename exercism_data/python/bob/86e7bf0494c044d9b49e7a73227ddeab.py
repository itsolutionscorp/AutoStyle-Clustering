#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    
    what=what.strip()
    
    response = 'Whatever.'     #default response
    
    if what == '':
        response = 'Fine. Be that way!'
    elif what.isupper():
        response = 'Whoa, chill out!'
    elif what[-1] == '?':
        response = 'Sure.'
    return response            #return response
