#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    # Remove trailing spaces
    what_trimmed = what.rstrip()
    
    # If 'what' is a yell
    if what_trimmed.isupper():
        return 'Whoa, chill out!'
    
    # If 'what' is a question
    elif what_trimmed.endswith('?'):
        return 'Sure.'
    
    # If 'what' is empty
    elif not what_trimmed:
        return 'Fine. Be that way!'
    
    # If 'what' is anything else
    else:
         return 'Whatever.'
