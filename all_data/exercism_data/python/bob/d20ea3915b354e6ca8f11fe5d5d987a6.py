# Python "Bob" exercise.
# Iteration 2

def hey(what):
    '''
    Return response based on a specific criteria
    received as input from bob_test.py
    '''
    if not what or what.isspace():
        return 'Fine. Be that way!'
    
    elif what.isupper():
        return 'Whoa, chill out!'

    elif what.endswith('?'):
        return 'Sure.'

    else:
        return 'Whatever.'
