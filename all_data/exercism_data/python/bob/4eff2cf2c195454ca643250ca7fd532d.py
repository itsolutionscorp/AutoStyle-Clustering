#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):

    text = what.strip()
    
    if not text:
        return 'Fine. Be that way!'
    elif text.isupper():
        return 'Whoa, chill out!'
    elif text.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
