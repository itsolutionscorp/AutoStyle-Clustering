#
# Submission file for the Python "Bob" exercise.
#
# v3: Simplified approach using native python functionality
# v2: Grouped regex into nested if statements
# v1: Approached problem with regex

def hey(what):
    '''
    Return string based on input from bob_test.py
    '''
    # Strip whitespace
    string = what.strip()

    # Are there uppercase characters in our string?
    if string.isupper(): return 'Whoa, chill out!'

    # Does the string end with a question mark?
    elif string.endswith("?"): return 'Sure'

    # Is the string empty?
    elif not string: return 'Fine. Be that way!'

    # Default return
    else: return 'Whatever.'
