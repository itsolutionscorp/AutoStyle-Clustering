#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip() #Remove any confusing leading or trailing spaces
    if not what:        # empty strings are false
        return 'Fine. Be that way!'
    elif what.isupper():
        return 'Whoa, chill out!'
    elif what.endswith('?'):
        return 'Sure.'
    elif not what.isupper():
        return 'Whatever.'
    return
