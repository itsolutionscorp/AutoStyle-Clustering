#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    what = what.strip()
    # Shouting - all capital letters
    if what.isupper():
        return 'Whoa, chill out!'
    # Questions - ending with ?
    # and optionally followed by arbitraily many spaces 
    elif what.endswith('?'):
        return 'Sure.'
    # Silences - all whitespace characters
    elif what == '':
        return 'Fine. Be that way!'
    return 'Whatever.'
