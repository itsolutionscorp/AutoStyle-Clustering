#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    onlywhat = what.strip() # Get rid of all the spaces.
    if (onlywhat == ''): # If we don't say anything.
        return 'Fine. Be that way!'
    if (onlywhat.isupper()): # If the string contains only capital letters and other signs.
        return 'Whoa, chill out!'
    if (onlywhat.endswith('?')): # If this is a calm question.
        return 'Sure.'
    return 'Whatever.' # Anything else.
