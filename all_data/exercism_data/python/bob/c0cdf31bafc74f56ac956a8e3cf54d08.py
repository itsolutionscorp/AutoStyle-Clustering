#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what.isupper():
        return 'Whoa, chill out!'
    elif what.isspace() or not what:
        return 'Fine. Be that way!'
    elif '?' == what.strip()[-1]:
        return 'Sure.'
    else:
        return 'Whatever.'
