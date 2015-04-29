"""This module implements 'Bob' the lackadaisical teenager."""
#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    """Parse Bob's input and return his response as a string."""
    # Catch empty strings first to avoid out of range errors
    if len(what) == 0:
        return 'Fine. Be that way!'
    
    # Precendence order: all caps is shouting, regardless of question
    if what.isupper():
        return 'Whoa, chill out!'
    if what[-1] == '?':
        return 'Sure.'
        
    # Check if there are any alphanum chars
    containsalnum = False
    for char in what:
        if char.isalnum():
            containsalnum = True
    if containsalnum:
        return 'Whatever.'
    
    # String must be empty or contain no alphanum chars.
    return 'Fine. Be that way!'
