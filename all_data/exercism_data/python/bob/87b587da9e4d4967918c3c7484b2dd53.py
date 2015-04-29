#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):   
    # Clean the string by removing leading/trailing whitespaces
    what = what.strip()
    
    # We're silent if the string is empty
    isSilent = not what
    
    # We're asking a question if the last character in the string is a question mark
    isQuestion = what.endswith('?')
    
    # We're yelling if the string contains all uppercase characters
    isYelling = what.isupper()
    
    if isSilent:
        response = 'Fine. Be that way!'
    elif isYelling:
        response = 'Whoa, chill out!'
    elif isQuestion:
        response = 'Sure.'
    else:
        response = 'Whatever.'
        
    return response
