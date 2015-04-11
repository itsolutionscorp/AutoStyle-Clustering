#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    # Check input is silence or prolonged silence
    if not what or what.isspace():
        return 'Fine. Be that way!'

    # Check if input is shout
    if what.isupper():
        return 'Whoa, chill out!'
    elif what.endswith('?'):
        # Or if sentence ends with a ?
        return 'Sure.'
    else:
        # Otherwise
        return 'Whatever.'
