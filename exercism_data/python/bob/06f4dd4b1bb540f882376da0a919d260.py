#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    
    response = ""


    if not what.strip():
       response = "Fine. Be that way!" 
    elif what.isupper():
        response = "Whoa, chill out!"
    elif what.rstrip().endswith('?'):
        response = "Sure."
    else:
        response = "Whatever."
        
        
    return response
