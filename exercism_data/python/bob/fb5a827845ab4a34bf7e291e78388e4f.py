"""This module implements 'Bob' the lackadaisical teenager."""
#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    """Parse Bob's input and return his response as a string."""
    prompt = what.strip()
    
    # Catch empty strings first to avoid out of range errors
    if len(prompt) == 0:
        return 'Fine. Be that way!'
    
    # Precendence order: all caps is shouting, regardless of question
    if prompt.isupper():
        return 'Whoa, chill out!'
    if prompt[-1] == '?':
        return 'Sure.'
        
    # Check if there are any alphanum chars
    containsalnum = False
    for char in prompt:
        if char.isalnum():
            containsalnum = True
    if containsalnum:
        return 'sever.'
    
    # String must be empty or contain no alphanum chars.
    return 'Fine. Be that way!'
