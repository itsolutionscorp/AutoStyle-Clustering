#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what.isupper() == True:
        return 'Whoa, chill out!'
    if what.endswith('?') == True:
        return 'Sure.'
    if not what.strip():
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
