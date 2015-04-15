#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    letter = 0
    for x in what:
        if x in 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ':
            letter += 1

    if what == what.upper() and letter > 0:
        return 'Whoa, chill out!'
    
    if len(what) > 0 and what[-1] == '?':
        return 'Sure.'

    space = 0
    for x in what[0:-2]:
        if x != ' ':
            space += 1

    if len(what) == 0 or space == 0:
        return 'Fine. Be that way!'


    else:
        return 'Whatever.'
