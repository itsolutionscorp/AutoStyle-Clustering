#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if (what.upper() == what and any(c.isalpha() for c in what)):
        return 'Whoa, chill out!'
    elif (what.rstrip().endswith('?')):
        return 'Sure.'
    elif (what.strip() == ''):
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
