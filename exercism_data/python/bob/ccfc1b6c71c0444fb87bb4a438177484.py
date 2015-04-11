#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if not what.strip(' \t'):
        return 'Fine. Be that way!'
    elif(what.upper() != what.lower() and what == what.upper()):
        return 'Whoa, chill out!'
    elif(what.endswith('?')):
        return 'Sure.'
    return 'Whatever.'
