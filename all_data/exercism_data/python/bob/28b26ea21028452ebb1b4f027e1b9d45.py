#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    stripped_string = what.strip()
    if stripped_string == '':
        return'Fine. Be that way!'
    elif stripped_string.isupper():
        return 'Whoa, chill out!'
    elif stripped_string.endswith('?'):
        return 'Sure.'
    return 'Whatever.'
