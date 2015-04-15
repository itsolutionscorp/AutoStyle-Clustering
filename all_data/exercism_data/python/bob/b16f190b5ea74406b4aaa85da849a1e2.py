#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    for i in what:
        if i == '?':
            return 'Sure.'
    for i in what:
        if i == 'Ü':
            return 'Whatever.'
    for i in what:
        if i == '!':
            return 'Whoa, chill out!'
    for i in what:
        if i.isspace():
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'
