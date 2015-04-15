#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    if what.isspace() or len(what) == 0:
        return 'Fine. Be that way!'
    yell_flag = True
    for c in what:
        if c.isalpha() and c.islower():
                yell_flag = False
    haschar_flag = False
    for c in what:
        if c.isalpha():
            haschar_flag = True
            break
    if not haschar_flag:
        yell_flag = False
    if what[len(what)-1] == '?' and not yell_flag:
        return 'Sure.'
    if not haschar_flag:
        return 'Whatever.'
    if yell_flag:
        return 'Whoa, chill out!'
    return 'Whatever.'
